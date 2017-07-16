<%@ taglib prefix="region" uri="http://www.casic203.com/region/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<!-- BEGIN SIDEBAR -->
<script src="${ctx}/s/gis/config.js" type="text/javascript" charset="UTF-8"></script>
<div class="page-sidebar nav-collapse collapse">

    <ul class="page-sidebar-menu">
            <li>

                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

                <div class="sidebar-toggler hidden-phone" ></div>

                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

            </li>
        <%--<region:region-permission permission="运行总览:read" region="app.4">
            <!--对象-->
            <li class="${currentMenu == 'gis' ? 'start active' : ''}">

                <a href="${ctx}/content/gis/index.jsp" target="_top">

                    <i class="icon-home"></i>

                    <span class="title">运行总览</span>

                    <span class="selected"></span>

                </a>

            </li>
            <!--对象模型编辑-->
        </region:region-permission>--%>
        <region:region-permission permission="组织机构:read" region="app.4">
            <!--组织结构管理-->
            <li class="${currentMenu == 'org' ? 'start active' : ''}">
                <a href="${ctx}/content/user/department-info-list.jsp" target="_top">

                    <i class="icon-cogs"></i>

                    <span class="title">组织机构</span>

                    <span class="selected"></span>

                </a>

            </li>
            <!--组织机构管理-->
        </region:region-permission>
        <region:region-permission permission="权限管理(平台):read" region="app.4">
            <!--权限管理-->
            <li class="${currentMenu == 'auth' ? 'start active' : ''}">
                <!--TODO LIST:换成相应模块下的jsp文件-->
                <a href="${ctx}/content/user/app-info-list.jsp" target="_top">
                    <i class="icon-cogs"></i>

                    <span class="title">权限管理</span>

                    <span class="selected"></span>

                </a>

            </li>
            <!--权限管理-->
        </region:region-permission>

        <region:region-permission permission="系统管理(平台):read" region="app.4">
            <!--系统管理-->
            <li class="${currentMenu == 'sys' ? 'start active' : ''}">

                <a href="${ctx}/content/sys/sys-log-list.jsp">

                    <i class="icon-cogs"></i>

                    <span class="title">系统管理</span>

                    <span class="selected"></span>

                </a>

            </li>
            <!--系统管理-->
        </region:region-permission>
    </ul>

</div>

<!-- END SIDEBAR MENU -->
