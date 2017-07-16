
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="header navbar navbar-inverse navbar-fixed-top">

    <div class="navbar-inner">

        <div class="head-img">

            <span class="left-img"><img src="${ctx}/images/basic/banner_L1.png" style="height:48px;"/></span>

            <%--<span class="center-img"><img src="${ctx}/images/basic/top-center.png" style="height:48px;"/></span>--%>

            <span class="right-img"><img src="${ctx}/images/basic/banner_R1.png" style="height:48px;"/></span>

        </div>

        <div class="back-img">

            <img style="height:37px;" src="${ctx}/images/basic/sys_ban.png"/>

            <div class="log-info">
                <a style="margin-left: 10px;">当前用户：${(empty sys_login_person) ? "未登录" : sys_login_person.getUserName().toString()}</a>
<%--
                <a style="margin-left: 10px; cursor: pointer" onclick="logout()">注销</a>
--%>
                <a style="margin-left: 10px; cursor: pointer" href="${casServerLoginUrl}/logout?service=${serverUrl}">注销</a>
            </div>

        </div>
    </div>
    <script>
        //退出登录
        var logout = function(){
            window.location.href='${ctx}/user/login-out.do'
            <%--window.location.href='${ctx}/user/login.do';--%>
        }
    </script>

    <%--<div class="container-fluid">--%>
    <%--</div>--%>

</div>

<div class="space9"></div>
