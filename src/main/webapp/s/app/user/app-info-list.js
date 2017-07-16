var SystemApp = function () {

    var oTable;
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
                        //$("#url_sortdata").val(data.aaData);
                        fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式
                    }
                });
            }

            oTable =  $('#table_app').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    { "bSortable": false },
                    null,
                    { "bSortable": false },
                    { "bSortable": false }
                ],
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
                "sAjaxSource": $('#context').val()+"/app/app-info-list.do", //TODO LIST:修改成对应的后台Controller地址
                "fnServerData":retrieveData,
                "oLanguage": {
                    "sSearch":"分系统名称:",
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
                    "mDataProp" : "name",
                    "sWidth" : '210px'
                }, {
                    "mDataProp" : "code",
                    "sWidth" : '120px'
                },  {
                    "mDataProp" : "descn"
                }, {
                    "mDataProp" : "btnEdit",
                    "sWidth" : '60px'
                }, {
                    "mDataProp" : "btnDelete",
                    "sWidth" : '60px'
                } ],
                "aoColumnDefs": [{
                    'bVisible':false,
                    'aTargets': [0]
                },{
                    'bSortable': false,
                    'aTargets': [1,2,3,4,5]
                }]
            });

            jQuery('#table_app_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#table_app_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#table_app_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            });

            //TODO LIST:删除资源n操作
            $('#table_app a.red').live('click', function (e) {
                e.preventDefault();

                if (confirm("确认要删除该操作 ?") == false) {
                    return;
                }
                var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
                var id = aData.id;
                $.ajax( {
                    type: "POST",
                    url: $('#context').val()+"/app/app-info-delete.do",
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
            $('#table_app a.blue').live('click', function (e) {
                e.preventDefault();
                var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
                var id = aData.id;
                location.href = $("#context").val() + "/app/app-info-edit.do?id=" + id;
            });

            $('#app_add').live('click', function (e) {
                //TODO LIST：提交到相应的.do url，导入到url连接
                location.href = $('#context').val()+"/app/app-info-edit.do";
            });
        }
    };
}();