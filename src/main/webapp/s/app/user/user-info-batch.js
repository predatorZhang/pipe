/**
 * Created by Administrator on 2015/7/26.
 */
var UserInfoBatch = function () {

    return {

        initForms: function () {

            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);

            $('#cancelBtn').live('click', function (e) {

                location.href = $("#context").val() + "/content/user/user-info-manage.jsp";

            });

            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    shpFile: {
                        required: true
                    }
                },
                messages: { // custom messages for radio buttons and checkboxes
                    shpFile: {
                        required: "Excel文件不能为空！"
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
                    var fileName=$('#dbfFile').val();
                    if(fileName.indexOf(".xls")==-1||fileName.indexOf(".xlsx")==-1){
                        alert("请选择excel文件");
                        return;
                    }
                    $('#submit_form').ajaxSubmit({

                        type: 'post',

                        url: $('#context').val() + "/user/add-user-batch.do",

                        dataType: 'json',

                        success: function (responseText, statusText, xhr, $form) {

                            if(responseText.success){
                                alert(responseText.message+"初始密码为:用户名123");
                            }else{
                                alert(responseText.message);
                            }
                            location.href = $("#context").val() + "/content/user/user-info-manage.jsp";

                        },

                        error: function (XmlHttpRequest, textStatus, errorThrown) {

                            console.log(XmlHttpRequest);

                            console.log(textStatus);

                            console.log(errorThrown);

                            alert("导入失败!");
                            location.href = $("#context").val() + "/content/user/user-info-manage.jsp";
                        }

                    });


                }
            });

            $('#btnRK').live("click", function (e) {
                e.preventDefault();
            });

            $('a.fileupload-exists').live('click', function (e) {
                e.preventDefault();
            })
        }

    };

}();