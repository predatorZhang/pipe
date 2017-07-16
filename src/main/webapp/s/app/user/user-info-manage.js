var UserInfoManage = function () {

    return {

        init: function () {

            function retrieveData( sSource, aoData, fnCallback ) {
                $.ajax( {
                    type: "POST",
                    url: sSource,
                    dataType:"json",
                    //TODO LIST：按条件查询服务器数据
                    data: "jsonParam="+JSON.stringify(aoData),
                    success: function(data) {
                        fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式
                    }
                });
            }

            var oTable =  $('#table_user').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "bAutoWidth":false,
                "bServerSide": true,
                "bPaginate": true,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "sAjaxSource": $('#context').val()+"/user/user-info-list.do", //TODO LIST:修改成对应的后台Controller地址
                "fnServerData":retrieveData,
                "oLanguage": {
                    "sSearch":"用户名:",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "抱歉， 没有找到",
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "sInfoEmpty": "没有数据",
                    "oPaginate": {
                        "sPrevious":"上一页",
                        "sNext": "下一页",
                        "sLast": "末页",
                        "sFirst": "首页"
                    }
                },
                "aoColumns" : [  {
                    "mDataProp" : "id"
                },  {
                    "mDataProp" : "username",
                    "sWidth" : '150px'
                },  {
                    "mDataProp" : "tel",
                    "sWidth" : '150px'
                },  {
                    "mDataProp" : "address",
                    "sWidth" : '150px'
                },  {
                    "mDataProp" : "descn"
                }, {
                    "mDataProp" : "btnPass",
                    "sWidth" : '60px'
                }, {
                        "mDataProp" : "btnEdit",
                        "sWidth" : '60px'
                }, {
                    "mDataProp" : "btnDelete",
                    "sWidth" : '60px'
                }],
                "aoColumnDefs": [{
                    'bVisible':false,
                    'aTargets': [0,5]
                },{
                    'bSortable': false,
                    'aTargets': [1,2,3,4,5,6]
                } ]
            });

            jQuery('#table_user_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#table_user_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#table_user_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            });

            //TODO LIST:删除资源n操作
            $('#table_user a.red').live('click', function (e) {
                e.preventDefault();

                if (confirm("确认要删除该操作 ?") == false) {
                    return;
                }
                var nRow = $(this).parents('tr')[0];

                var aData = oTable.fnGetData(nRow);
                var id = aData.id;
                $.ajax({
                    type: "POST",
                    url: $('#context').val()+"/user/user-info-delete.do",
                    dataType:"json",
                    data: {
                        id : id
                    },
                    success: function(data) {

                        var jData = eval(data);

                        if(jData.success==true) {

                            oTable.fnDraw();

                        }

                        alert(jData.message);

                    },
                    error:function(request){

                        alert("删除失败");

                    }
                });

            });

            //TODO LIST:编辑用户操作
            $('#table_user a.blue').live('click', function (e) {

                e.preventDefault();

                var nRow = $(this).parents('tr')[0];

                var aData = oTable.fnGetData(nRow);

                var id = aData.id;

                location.href = $("#context").val() + "/user/user-info-change.do?id=" + id;

            });

            //TODO LIST:审核用户
            $('#table_user a.green').live('click', function (e) {

                e.preventDefault();

                var nRow = $(this).parents('tr')[0];

                var aData = oTable.fnGetData(nRow);

                var id = aData.id;

                $.ajax({

                    type: "POST",

                    url: $("#context").val() + "/user/pass-new-user.do",

                    dataType:"json",

                    data: {

                        id : id

                    },

                    success: function(data) {

                        var jData = eval(data);

                        if(jData.success==true) {

                            oTable.fnDraw();

                        }

                        alert(jData.message);

                    },
                    error:function(request){

                        alert("审核失败");

                    }
                });

            });

            $('#user_add').live('click', function (e) {
                //TODO LIST：提交到相应的.do url，导入到url连接
                location.href = $('#context').val()+"/user/user-info-change.do";
            });

            $('#user_batch').live('click', function (e) {
                //TODO LIST：提交到相应的.do url，导入到url连接
                location.href = $('#context').val()+"/user/user-info-batch.do";
            });

            $('#user_exp').live('click', function (e) {
                //TODO LIST：提交到相应的.do url，导入到url连接
                location.href = $('#context').val()+"/user/user-info-exp.do";
            });
        }
    };
}();