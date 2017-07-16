<%@ taglib prefix="region" uri="http://www.casic203.com/region/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<!-- BEGIN SECOND MENU -->

<ul class="breadcrumb">
    <region:region-permission permission="注册服务管理:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/service/service-info-list.jsp">注册服务管理</a>

        </li>
    </region:region-permission>

    <region:region-permission permission="在线服务申请:read" region="app.4">

        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/service/service-app-list.jsp">在线服务申请</a>

        </li>
    </region:region-permission>
    <region:region-permission permission="服务申请历史:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/service/service-app-history.jsp">服务申请历史</a>

        </li>
    </region:region-permission>
    <region:region-permission permission="审核管理（服务）:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/service/service-check-list.jsp">审核管理</a>

        </li>
    </region:region-permission>
    <%--<region:region-permission permission="CTRL_服务统计:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/service/service-tj-info.jsp">服务统计</a>

        </li>
    </region:region-permission>
    <region:region-permission permission="CTRL_服务访问统计:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/service/service-fwtj-info.jsp">服务访问统计</a>

        </li>
    </region:region-permission>--%>
</ul>

<!-- END SECOND MENU -->
