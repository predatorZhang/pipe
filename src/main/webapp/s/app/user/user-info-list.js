var UserInfoList = function () {

    return {

        init: function () {

            function retrieveData( sSource, aoData, fnCallback ) {
                $.ajax( {
                    type: "POST",
                    url: sSource,
                    dataType:"json",
                    //TODO LIST：按条件查询服务器数据
                    data: {
                        jsonParam:JSON.stringify(aoData),
                        type:'1'
                    },
                    success: function(data) {
                        //$("#url_sortdata").val(data.aaData);
                        fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式

                        var nCloneTh = document.createElement( 'th' );
                        var nCloneTd = document.createElement( 'td' );

                        nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';

                        $('#table_user thead tr').each( function () {
                            if(this.childNodes.length<5)
                            {
                                this.insertBefore( nCloneTh, this.childNodes[0] );
                            }
                        } );

                        $('#table_user tbody tr').each( function () {
                            this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
                        } );
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
                    "mDataProp" : "postName",
                    "sWidth" : '150px'
                },  {
                    "mDataProp" : "depName",
                    "sWidth" : '150px'
                },  {
                    "mDataProp" : "descn"
                }, {
                    "mDataProp" : "btnEdit",
                    "sWidth" : '60px'
                }, {
                    "mDataProp" : "appNames",
                    "sWidth" : '210px'
                },  {
                    "mDataProp" : "groupNames"
                }],
                "aoColumnDefs": [{
                    'bVisible':false,
                    'aTargets': [0,2,6,7]
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

            var fnFormatDetails = function( oTable, nTr ){
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table>';
                sOut += '<tr><td>应用:</td><td>'+aData.appNames+'</td></tr>';
                sOut += '<tr><td>群组:</td><td>'+aData.groupNames+'</td></tr>';
                sOut += '</table>';
                return sOut;
            }

            $('#table_user').on('click', ' tbody td .row-details', function () {
                var nTr = $(this).parents('tr')[0];
                if ( oTable.fnIsOpen(nTr) )
                {
                    /* This row is already open - close it */
                    $(this).addClass("row-details-close").removeClass("row-details-open");
                    oTable.fnClose( nTr );
                }
                else
                {
                    /* Open this row */
                    $(this).addClass("row-details-open").removeClass("row-details-close");
                    oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
                }
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
                $.ajax( {
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
                //TODO LIST：提交userId，跳转到指定的do文件.
                var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
                var id = aData.id;
                location.href = $("#context").val() + "/user/user-info-edit.do?id=" + id;

            });

            $('#user_add').live('click', function (e) {
                //TODO LIST：提交到相应的.do url，导入到url连接
                location.href = $('#context').val()+"/user/user-info-edit.do";
            });
        }
    };
}();