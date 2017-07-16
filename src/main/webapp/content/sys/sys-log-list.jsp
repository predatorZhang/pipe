<%@page contentType="text/html;charset=UTF-8" %>

<%@include file="/taglibs.jsp" %>

<%pageContext.setAttribute("currentMenu", "sys");%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if IE 10]> <html lang="en" class="ie10"> <![endif]-->

<!--[if IE 11]> <html lang="en" class="ie11"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<head>

    <meta charset="utf-8"/>

    <!--TODO LIST:修改为对应系统-->
    <title>统一用户及权限管理系统</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="${ctx}/s/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/s/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/s/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/s/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/s/media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/s/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/s/media/css/blue.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="${ctx}/s/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link rel="stylesheet" type="text/css" href="${ctx}/s/media/css/select2_metro.css"/>

    <link rel="stylesheet" href="${ctx}/s/media/css/DT_bootstrap.css"/>

    <link rel="stylesheet" type="text/css" href="${ctx}/s/media/css/datepicker.css"/>

    <!--TODO List:此处统一换成网站的图标-->
    <link rel="shortcut icon" href="${ctx}/s/media/image/ht.jpg"/>

    <!-- END PAGE LEVEL STYLES -->

    <style type="text/css">

        #table_log th {
            font-size: 10pt;
            color: #ffffff;
        }

        #table_log thead tr {
            background-color: #3D3D3D;
        }

    </style>


</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed" scroll="no">

<input type="hidden" id="context" value="${ctx}">

<!-- BEGIN HEADER -->

<%@include file="/common/layout/header.jsp" %>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <%@include file="/common/layout/menu.jsp" %>

    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <div class="clearfix"></div>

            <!--BEGIN SECOND MENU-->

            <%@include file="/common/layout/second-menu-sys.jsp" %>

            <!--BEGIN SECOND MENU-->

            <!--Start the content-->

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <!--TODO LIST：换成模块名称，即使一级标题的名称-->
                            <div class="caption"><i class="icon-edit"></i>日志管理</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                            </div>

                        </div>

                        <div class="portlet-body" style="overflow-y: auto;height: 400px">

                            <div class="row-fluid">

                                <table class="table" style="border: none">

                                    <tr style="border: none">
                                        <td style="border: none;line-height: 34px;">
                                            日志类型
                                        </td>
                                        <td style="border: none">
                                            <select class="m-wrap chosen" id="log-type" name="type">
                                                <option value=" "> </option>
                                                <option value="系统日志">系统日志</option>

                                                <option value="运行日志">运行日志</option>

                                                <option value="错误日志">错误日志</option>

                                                <option value="报警日志">报警日志</option>

                                            </select>

                                        </td>

                                        <%--<td style="border: none">
                                            <input id="log-type" type="text" class="m-wrap input-medium"/>
                                        </td>--%>
                                        <td style="border: none;line-height: 34px;">
                                            起始日期
                                        </td>
                                        <td style="border: none; width: 280px;">
                                            <div class="m-wrap input-append date date-picker"
                                                 data-date-format="yyyy-mm-dd" data-date-viewmode="years">
                                                <input id="begin-day" class="m-wrap m-ctrl-medium date-picker input-small" size="10"
                                                       type="text"
                                                       value=""/><span class="add-on"><i
                                                    class="icon-calendar"></i></span>
                                            </div>
                                            -
                                            <div class="m-wrap input-append date date-picker"
                                                 data-date-format="yyyy-mm-dd" data-date-viewmode="years">
                                                <input id="end-day" class="m-wrap m-ctrl-medium date-picker input-small" size="10"
                                                       type="text"
                                                       value=""/><span class="add-on"><i
                                                    class="icon-calendar"></i></span>
                                            </div>
                                        </td>
                                        <td style="border: none">
                                            <button id="app_query" class="btn green">
                                                日志查询 <i class="icon-plus"></i>
                                            </button>
                                        </td>
                                        <td style="border: none">
                                            <button id="app_exp" class="btn green">
                                                日志导出 <i class="icon-plus"></i>
                                            </button>
                                        </td>
                                    </tr>

                                </table>

                                <!--TODO LIST:此处把table_user改成相关子模块的ID即可-->
                                <table class="table table-striped table-bordered table-hover" id="table_log">

                                    <thead>

                                    <tr>

                                        <th>ID</th>

                                        <th>类型</th>

                                        <th>日期</th>

                                        <th>内容</th>

                                        <th>操作人</th>

                                        <th>查看</th>

                                    </tr>

                                    </thead>

                                </table>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

            <!--End the content-->

        </div>

    </div>

    <!-- END PAGE CONTAINER-->

    <div class="clearfix"></div>

</div>

<!-- END PAGE -->

<iframe id="sysLogMsgFrame" src="" style="display:none; border: solid 1px #852B99;" scrolling="yes" width="700px"
        height="300px"></iframe>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<div class="footer">

    <div class="footer-inner">

        2015 &copy; CASIC203.

    </div>

    <div class="footer-tools">

                <span class="go-top">

                <i class="icon-angle-up"></i>

                </span>

    </div>

</div>

<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS -->

<script src="${ctx}/s/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="${ctx}/s/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="${ctx}/s/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="${ctx}/s/media/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="${ctx}/s/media/js/excanvas.min.js"></script>

<script src="${ctx}/s/media/js/respond.min.js"></script>

<![endif]-->

<script src="${ctx}/s/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${ctx}/s/media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="${ctx}/s/media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="${ctx}/s/media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="${ctx}/s/media/js/select2.min.js"></script>

<script type="text/javascript" src="${ctx}/s/media/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="${ctx}/s/media/js/DT_bootstrap.js"></script>

<script type="text/javascript" src="${ctx}/s/media/js/bootstrap-datepicker.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="${ctx}/s/media/js/app.js" type="text/javascript"></script>

<script src="${ctx}/s/app/sys/sys-log-list.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>

    $(function () {

        App.init(); // initlayout and core plugins

        SysLogList.init();

    })

</script>

<!-- END JAVASCRIPTS -->

<!-- END BODY -->
</body>

</html>

