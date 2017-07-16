package com.casic.accessControl.phone.web;

import com.casic.accessControl.phone.manager.PhoneManager;
import com.casic.accessControl.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by test203 on 2015/11/4.
 */
@Controller
@RequestMapping("phone")
public class PhoneController {

    private Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Resource private PhoneManager phoneManager;

    public void setPhoneManager(PhoneManager phoneManager) {
        this.phoneManager = phoneManager;
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(){
        String msg = "";
        try{
            PropertiesUtil propUtil = new PropertiesUtil("application.properties");
            String username = propUtil.getProperty("webservice.username");
            String password = propUtil.getProperty("webservice.password");
            String method = propUtil.getProperty("webservice.method");
            logger.info("================================HPHONE1===========================================");
            logger.info("账户："+username);
            logger.info("密码："+password);
            logger.info("=================================================================================");
            msg = phoneManager.getToken(username, password, method);
        }catch (Exception e){
            msg = e.toString();
        }
        return msg;
    }

}
