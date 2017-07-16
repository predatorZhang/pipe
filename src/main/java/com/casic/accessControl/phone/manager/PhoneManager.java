package com.casic.accessControl.phone.manager;

import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.phone.dto.LoginResult;
import com.casic.accessControl.util.PropertiesUtil;
import com.casic.accessControl.util.StringUtils;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by test203 on 2015/11/3.
 */
@org.springframework.stereotype.Service
public class PhoneManager {
    private Logger logger = LoggerFactory.getLogger(PhoneManager.class);
    private JsonMapper mapper = new JsonMapper();
    private PropertiesUtil propUtil = new PropertiesUtil("application.properties");
    public String getToken(String username, String password, String method) {
        LoginResult result = null;
        logger.info("==================================BEGIN GET TOKEN=========================================");
        try {
            String endpoint = propUtil.getProperty("webservice.endpoint");
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(method);//WSDL里面描述的操作名称
            call.addParameter("username", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//操作的参数
            call.addParameter("password", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//操作的参数
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型
            call.setUseSOAPAction(true);
            Object[] obj = new Object[]{username, password};
            logger.info("==================================BEGIN CALL SERVICE=========================================");
            String json = (String) call.invoke(obj);
            logger.info("=================================="+json+"=========================================");
            if (StringUtils.isNotBlank(json)) {
                result = mapper.fromJson(json, LoginResult.class);
            }
            logger.info("==================================SUCCESS=========================================");
        } catch (Exception e) {
            logger.info("==================================EXCEPTION=========================================");
            e.printStackTrace();
        }
        if (null != result && null != result.getData()) {
            return result.getData().getToken();
        }
        return "";
    }

}
