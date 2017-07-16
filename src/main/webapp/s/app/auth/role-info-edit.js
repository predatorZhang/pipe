var RoleInfoEdit = function () {

    return {

        initForms:function(){

            $('#rescIds').multiSelect();
            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);

            $('#cancelBtn').live('click', function (e) {

                location.href = $("#context").val() + "/content/auth/role-info-list.jsp";

            });

            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    name: {
                        required: true
                    },
                    permIds: {
                        required: true
                    }
                },
                messages: { // custom messages for radio buttons and checkboxes
                    name: {
                        required: "角色名称不能为空！"
                    },
                    permIds: {
                        required: "必须挑选权限！"
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
                        url: $('#context').val()+"/role/role-info-save.do",
                        //  dataType:'json',
                        //TODO LIST：按条件查询服务器数据
                        //   data: JSON.stringify($('#submit_form').serialize()),
                        data: $('#submit_form').serialize(),
                        success: function(data) {
                            var jData = eval(data);
                            if(jData.success==true) {

                                location.href = $("#context").val() + "/content/auth/role-info-list.jsp";
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
        },
        getResources:function(value){
            $.ajax( {
                type: "POST",
                url: $('#context').val()+"/role/get-app-rescs.do?appId="+value,
                dataType:"json",
                success: function(data) {
                    if(data.success){
                        $('#rescIds').empty();
                        if(data.rescs.length>0){
                            for(var i=0;i<data.rescs.length;i++){
                                $('#rescIds').append(
                                    $('<option></option>')
                                        .text(data.rescs[i].name)
                                        .val(data.rescs[i].id)
                                );
                            }
                        }
                        $('#rescIds').multiSelect('deselect_all');
                        $('#rescIds').multiSelect('refresh');
                    }
                },
                error:function(request){
                    //error.show();
                }
            });

            //$('#permIds').multiSelect('addOption', { value: 'test', text: 'test'});
        }
    };

}();