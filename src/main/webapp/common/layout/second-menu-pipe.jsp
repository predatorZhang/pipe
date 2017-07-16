<%@ taglib prefix="region" uri="http://www.casic203.com/region/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<!-- BEGIN SECOND MENU -->

<ul class="breadcrumb">
    <region:region-permission permission="管线资源管理:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/pipe/pipe-info-list.jsp">管线资源管理</a>

        </li>
    </region:region-permission>
    <region:region-permission permission="在线管线资源申请:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/pipe/pipe-app-list.jsp">在线管线资源申请</a>

        </li>
    </region:region-permission>
    <region:region-permission permission="管线资源申请历史:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/pipe/pipe-app-history.jsp">管线资源申请历史</a>

        </li>
    </region:region-permission>
    <region:region-permission permission="审核管理（管线）:read" region="app.4">
        <li>

            <i class="icon-cogs"></i>

            <a href="${ctx}/content/pipe/pipe-check-list.jsp">审核管理</a>

        </li>
    </region:region-permission>
</ul>

<!-- END SECOND MENU -->
