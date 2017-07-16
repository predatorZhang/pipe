package com.casic.accessControl.rs;

import com.casic.accessControl.app.manager.AppManager;
/*import com.casic.accessControl.org.domain.UserInfo;*/
import com.casic.accessControl.permission.UserObj;
import com.casic.accessControl.org.manager.UserInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Component
@Path("authority")
public class AuthorityResource
{
    private static Logger logger = LoggerFactory.getLogger(AuthorityResource.class);

    private UserInfoManager userInfoManager;

    private AppManager appManager;

    @Resource
    public void setUserInfoManager(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @Resource
    public void setAppManager(AppManager appManager) {this.appManager = appManager;}

    @GET
    @Path("user")
    @Produces({"application/json", "application/xml;charset=UTF-8"})
    public UserObj getUser(@QueryParam("username") String username,@QueryParam("password") String password, @QueryParam("appId") String appId)
    {
        if (appId == null) {
            logger.warn("appId cannot be null");
        }
        UserObj userObj = null;
        try {
            username= URLDecoder.decode(username,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn(e.getMessage());
        }
        try {
            userObj = userInfoManager.validateUser(username,password,appId);
        }catch (Exception e){
            userObj = new UserObj();
            e.printStackTrace();
        }
        return userObj;
    }

}

