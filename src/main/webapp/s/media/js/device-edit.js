var DevicEdit = function () {

    var globalControl = null;
    var locaServerIp = "192.168.0.203";
    var locaServerPort = "1500";
    var defaultRelativeDir="http://192.168.0.203/locaspace/gcm/";
    var dbServer = "192.168.0.203/FZDB";
    var dbUserName = "scott";
    var dbPassword = "fzdb";
    var ds=null;
    var groundLayerName = "180fd";
    var deviceLayer = null;

    var detectActiveX = function () {

        try {

            var comActiveX = new ActiveXObject("LOCASPACEPLUGIN.LocaSpacePluginCtrl.1");

        } catch (e) {

            return false;

        }

        return true;

    };

    var initGlobal = function(){

        if (detectActiveX() == false) {

            var earthDiv = document.getElementById("earthDiv");
            earthDiv.innerHTML = "<OBJECT ID=\"MyGlobal\" CLASSID=\"CLSID:0E7A33FF-6238-41A6-A38D-AC3F755F92B6\" WIDTH=\"100%\" HEIGHT=\"100%\"><param name=\"wmode\" value=\"transparent\"></OBJECT>";

        }
        else {
            try {
                var earthDiv = document.getElementById("earthDiv");
                earthDiv.innerHTML = "<OBJECT ID=\"MyGlobal\" CLASSID=\"CLSID:0E7A33FF-6238-41A6-A38D-AC3F755F92B6\" WIDTH=\"100%\" HEIGHT=\"100%\"><param name=\"wmode\" value=\"transparent\"></OBJECT>";
                globalControl = document.getElementById("MyGlobal");

                globalControl.Globe.LatLonGridVisible = false; // 设置三维控件的经纬度为不显示

            } catch (e) {
                alert(e.description);
            }
        }

    };

    var connectServer = function() {
        try {
            if (globalControl != null) {
                //连接locaserver
                globalControl.Globe.ConnectServer(locaServerIp, locaServerPort, "", "");

                globalControl.Globe.GroundOpaque = 0.5;
                globalControl.Globe.DefaultRelativeDir = defaultRelativeDir;

                //连接ORACLE数据库

                 ds=globalControl.Globe.DataManager.OpenOracleDataSource(dbServer,"","",dbUserName,dbPassword);
                 globalControl.refresh();

                 if(null!=ds)
                 {
                     for ( var i = 0; i < ds.Count; i++)
                     {
                         var layer = globalControl.Globe.Layers.Add2(ds.Item(i));
                     }
                 }


                globalControl.refresh();

            }
        } catch (e) {
            alert(e.description);
        }
    };

    var handleDatePickers = function () {

        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl : App.isRTL()
            });
        }
    }

    var addFeature = function(layerName,featureName,fieldName,fieldValue,modelPath,position,altitudeMode){
        if(ds!=null){
            for(var i = 0;i<ds.Count;i++){
                if(ds.Item(i).Caption==layerName){
                    var layer=globalControl.Globe.Layers.Item(ds.Item(i).Caption);
                    if(layer!=null){
                        var feature = layer.Dataset.CreateFeature();
                        var model = globalControl.CreateGeoModel();
                        model.FilePath=modelPath;
                        model.Position=position;
                        model.AltitudeMode=altitudeMode;
                        feature.Geometry = model;
                        feature.Name = featureName;
                        feature.SetFieldValue(fieldName, fieldValue);//第一个参数是 字段的名称，使用字段名称作为第一个参数的函数没有封装出来

                        //修复bug GXZTCGXT_BUG_035(1、编号以及编码都显示空白)
                        feature.SetFieldValue("井深", 0);
                        feature.SetFieldValue("编码", featureName);

                        layer.AddFeature(feature);
                        layer.Save();
                    }
                }
            }
        }
        else{
            alert("增加feature失败");
        }
    };

    var deleteFeature = function(featureName,layer){

        if(featureName != null && layer != null){
            var features= layer.GetFeatureByName(featureName,false);
            for(var i=0;i<features.Count;i++){
                feature = features.Item(i);
                feature.Delete();
            }
            layer.Save();
            globalControl.Refresh();
        }
    };

    var updateFilepathAndFieldvalue = function(featureName,layer,modelPath){

        if(layer == null||featureName==null||modelPath==null){
            return;
        }
        var features = layer.GetFeatureByName(featureName,false);
        for ( var j = features.Count -1; j >= 0; j--) {
            var feature = features.Item(j);
            if (feature != null && feature.Geometry!=null && feature.Geometry.Type==305) {
                var model = feature.Geometry;
                model.FilePath = modelPath;
                layer.save();
            }
        }
    };

    var addDev = function(result) {
        if(result.typeName.indexOf("光纤")!=-1){
            return;
        }
        var position = globalControl.CreatePoint3d();
        position.SetValue(result.longtitude, result.latitude,result.height);
        addFeature("设备图层",result.devCode,"DEVICETYPE",result.typeName,result.location,position,0);
    }

    var editDev = function (result) {
        if( null==result || null==result.devCode || null==result.location){
            return;
        }

        for ( var i = 1; i < globalControl.Globe.Layers.Count + 1; i++) {
            var layer = globalControl.Globe.Layers.GetLayerByID(i);
            if (layer.Caption() == "设备图层") {
                updateFilepathAndFieldvalue(result.devCode,layer,result.location);
            }
        }
    }


    return {
        //main function to initiate the module
        init: function () {

            handleDatePickers();

            initGlobal();

            connectServer();

        },

        initForms:function(){

            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);

            $('#cancelBtn').live('click', function (e) {

                location.href = $("#context").val() + "/content/dev/device-list.jsp";

            });

            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    devCode: {
                        required: true
                    },
                    devName: {
                        required: true
                    },
                    deviceTypeId: {
                        minlength: 3,
                        required: true
                    },
                    gps: {
                        minlength: 3,
                        required: true
                    },
                    installPosition: {
                        minlength: 3,
                        required: true
                    },
                    simid: {
                        required: true,
                        mobile : true
                    },
                    userId: {
                        minlength: 3,
                        required: true
                    }
                },
                messages: { // custom messages for radio buttons and checkboxes
                    typeCode: {
                        remote: "该设备已被注册"
                    }
                },
                errorPlacement: function (error, element) { // render error placement for each input type

                    error.insertAfter(element); // for other inputs, just perform default behavoir

                },

                invalidHandler: function (event, validator) { //display error alert on form submit
                    success.hide();
                    error.show();
                    App.scrollTo(error, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                    $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change dony by hightlight
                    $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .addClass('valid ok') // mark the current input as valid and display OK icon
                        .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group

                },

                submitHandler: function (form) {
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                    $.ajax( {
                        type: "POST",
                        url: $('#context').val()+"/deviceInfo/deviceInfo-save.do",
                        //  dataType:'json',
                        //TODO LIST：按条件查询服务器数据
                        //   data: JSON.stringify($('#submit_form').serialize()),
                        data: $('#submit_form').serialize(),
                        success: function(data) {

                            var jData = eval(data);
                            if(jData.success==true) {
                                if(jData.md == "add")
                                {
                                    addDev(jData);
                                }

                                if(jData.md == "edit")
                                {
                                    editDev(jData);
                                }
                                location.href = $("#context").val() + "/content/dev/device-list.jsp";
                            }
                            else{

                                success.hide();
                                $("#errorMessage").html(jData.message);
                                error.show();

                            }
                        },
                        error:function(request){
                            success.hide();
                            error.show();
                        }
                    });
                }
            });
        }

    };

}();