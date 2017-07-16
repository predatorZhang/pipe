var Department = function () {

    var selectedNode;

    var form;

    var error;

    var success;


    var setting = {

        callback:{

            onClick:onNodeClick

        }

    };

     var resetForm = function(){

         $('#submit_form #name').val("");

         $('#submit_form #code').val("");

         $('#submit_form #id').val("0");

         $('#submit_form #parentId').val("0");

         success.hide();

         error.hide();

     };

    var onNodeClick = function(event, treeId, treeNode, clickFlag) {

        selectedNode = treeNode;

    };


    return {

        init: function () {

            Department.initTree();

            Department.initForms();

        },

        initTree:function(){

            $.ajax({

                url : $('#context').val() + "/dep/dep-info-list.do",

                type : 'POST',

                dataType : 'json',

                success: function(data) {

                    var treeObj  = $.fn.zTree.init($("#dep_tree"), {callback:{

                        onClick:onNodeClick

                    }}, eval(data));

                    treeObj.expandAll(true);

                }

            });

            //部门编辑按钮响应
            $('#btn_dep_edit').live('click', function (e) {

                //TODO LIST：编辑选中节点信息
                if(selectedNode==null) {

                    alert("请先选择对应待编辑部门");

                    return;
                }

                $('#submit_form #name').val(selectedNode.name);

                $('#submit_form #code').val(selectedNode.code);

                $('#submit_form #id').val(selectedNode.id);

                $('#submit_form #parentId').val(selectedNode.parentId);

            });

            //部门添加按钮响应
            $('#btn_dep_add').live('click', function (e) {

                resetForm();

                if(selectedNode!=null) {

                    $('#submit_form #parentId').val(selectedNode.id);

                }

            });

            //部门删除按钮响应
            $('#btn_dep_del').live('click', function (e) {

                if(selectedNode==null) {

                    alert("请先选择对应待删除部门");

                }

                if (confirm("确认要删除该部门 ?") == false) {
                    return;
                }

                //ajax删除节点
                $.ajax({

                    url : $('#context').val() + "/dep/dep-info-del.do",

                    type : 'POST',

                    dataType : 'json',

                    data: "id="+selectedNode.id,

                    success: function(data) {

                        if(data.success==true) {

                            location.href = $("#context").val() + "/content/user/department-info-list.jsp";
                        }
                        alert(data.message);
                    }

                });

            });

        },

        initForms:function(){

             form = $('#submit_form');

             error = $('.alert-error', form);

             success = $('.alert-success', form);

            //重置按钮
            $('#resetBtn').live('click', function (e) {

                resetForm();

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

                    code: {

                        required: true

                    }

                },

                messages: { // custom messages for radio buttons and checkboxes

                    name: {

                        required: "部门名称不能为空！"

                    },

                    code: {

                        required: "部门编码不能为空！"

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

                    $(element).closest('.help-inline').removeClass('ok'); // display OK icon

                    $(element).closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group

                },

                unhighlight: function (element) { // revert the change dony by hightlight

                    $(element).closest('.control-group').removeClass('error'); // set error class to the control group

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

                        url: $('#context').val()+"/dep/dep-info-save.do",

                        data: $('#submit_form').serialize(),

                        success: function(data) {

                            var jData = eval(data);

                            if(jData.success==true) {
                                success.show();

                                location.href = $("#context").val() + "/content/user/department-info-list.jsp";

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

                            alert("请先点击新增按钮");
                           // location.href = $("#context").val() + "/content/user/department-info-list.jsp";

                        }

                    });

                }

            });

        }

    };

}();