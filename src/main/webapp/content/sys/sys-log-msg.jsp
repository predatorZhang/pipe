<%@page contentType="text/html;charset=UTF-8" %>

<%@include file="/taglibs.jsp" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if IE 10]> <html lang="en" class="ie10"> <![endif]-->

<!--[if IE 11]> <html lang="en" class="ie11"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<head>

    <title>服务申请详情</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <meta http-equiv="X-UA-Compatible" content="IE=10"/>

    <link rel="stylesheet" type="text/css" href="${ctx}/s/jquery-easyui-1.4.3/themes/bootstrap/easyui.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/s/jquery-easyui-1.4.3/themes/icon.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/s/jquery-easyui-1.4.3/themes/color.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/s/jquery-easyui-1.4.3/demo.css">

    <script type="text/javascript" src="${ctx}/s/jquery-easyui-1.4.3/jquery.min.js"></script>

    <script type="text/javascript" src="${ctx}/s/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>

    <style>
        table {
            width: 660px;
        }

        table, tr, td {
            padding: 9px;
            font-family: 宋体;
            font-size: 10pt;
            border-collapse: collapse;
            border-spacing: 0;
            border: solid 1px #DDDDDD;
        }

        td {

        }
    </style>

    <script type="text/javascript">
        var closeWindow = function () {
            var iframe = window.parent.document.getElementById("sysLogMsgFrame");
            iframe.style.display = "none";
            iframe.src = "";
        }
    </script>

</head>

<body style="background-color: #FFFFFF;">

<div class="easyui-panel" style="padding:5px; text-align: right; background-color: #852B99;width: 660px;">
    <div style="float: left; color: #ffffff; font-weight: bold;line-height: 36px; padding-left: 9px; letter-spacing: 2px;">
        日志详细信息
    </div>
    <a href="#" class="easyui-linkbutton" style="font-weight: bold;color: #ffffff;" onclick="closeWindow();"
       data-options="plain:true,iconCls:'icon-cancel'">关闭</a>
</div>
<table>
    <tr>
        <td style="background-color: #F9F9F9;">
            日志类型
        </td>
        <td>
            ${model.logType}
        </td>
    </tr>
    <tr>
        <td style="background-color: #F9F9F9;">
            产生日期
        </td>
        <td>
            ${model.logDay}
        </td>
    </tr>
    <tr>
        <td style="background-color: #F9F9F9;">
            操作人
        </td>
        <td>${model.userName}</td>
    </tr>
    <tr>
        <td style="background-color: #F9F9F9;">
            内容
        </td>
        <td>
            ${model.msg}
        </td>
    </tr>
</table>

</body>
</html>