package com.casic.accessControl.core.auth;

import com.casic.accessControl.org.manager.UserInfoManager;
import com.casic.accessControl.permission.UserObj;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 2015/3/3.
 */
public class AuthFilter implements Filter

{
    @Resource
    private UserInfoManager userInfoManager;
    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(StringUtils.SYS_USER) == null)
        {
//            logger.info(request.getRequestURI());
//            if((request.getContextPath()+"/").equals(request.getRequestURI())){
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
//            }else {
//                response.sendRedirect(request.getContextPath() + "/user/ill-login.do?url="+request.getRequestURI());
//            }
//            return;


                String userName = request.getUserPrincipal().getName();
                UserObj userObj=userInfoManager.validateUser(userName, "default", "4");//设置权限用
                session.setAttribute("UserObj", userObj);
                session.setAttribute("sys_login_person", userObj);
                //TODO LIST:

        }

        request.setAttribute("casServerLoginUrl", casServerLoginUrl);
        request.setAttribute("serverUrl", serverUrl);
        filterChain.doFilter(request, response);
    }

    private String casServerLoginUrl;

    private String serverUrl;

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
