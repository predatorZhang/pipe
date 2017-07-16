package com.casic.accessControl.rs;

import com.casic.accessControl.rs.services.ServiceManager;
import com.casic.accessControl.rs.services.serviceDto.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("serviceController")
public class ServiceController
{
   private ServiceManager serviceManager;
    @GET
    @Path("getAlarmCountByPIpeTyeAndDate")
    @Produces({"application/json", "application/xml;charset=UTF-8"})
    public Map getAlarmCountByPIpeTyeAndDate(@QueryParam(value = "alarmDate") String alarmDate,
                       @QueryParam(value = "pipeType" ) String pipeType) throws Exception{
        Map map = new HashMap();

        pipeType=this.getPipeType(pipeType);
        try{
            List<AlarmPipeDTO> dtos=serviceManager.getAlarmCountByPIpeTyeAndDate(alarmDate, pipeType);
            if(dtos.size()==0){
                 dtos.add(new AlarmPipeDTO(new BigDecimal(0),pipeType,alarmDate));
            }else {
                for(AlarmPipeDTO alarmPipeDTO:dtos){
                    alarmPipeDTO.setPipeType(alarmPipeDTO.getPipeType().substring(0,alarmPipeDTO.getPipeType().indexOf("附属物")));
                }
            }
            map.put("success", true);
            map.put("data", dtos);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("errorMsg", "返回数据异常");
        }
        return map;
    }


    @GET
    @Path("getPipeHealthCountByPIpeTyeAndDate")
    @Produces({"application/json", "application/xml;charset=UTF-8"})
    public Map getPipeHealthCountByPIpeTyeAndDate(@QueryParam(value = "analyseDate") String alarmDate,
                                             @QueryParam(value = "pipeType" ) String pipeType) throws Exception{
        Map map = new HashMap();

        String pipeType1=this.getPipeType(pipeType);
        try{
            String pipeType2=serviceManager.getPipeTypeCode(pipeType1);
            List<HealthPipeDTO> dtos=serviceManager.getPipeHealthCountByPIpeTyeAndDate(alarmDate, pipeType2);
            if(dtos.size()==0){
                dtos.add(new HealthPipeDTO(new BigDecimal(0), pipeType1, alarmDate, ""));
            }else {
                if("".equals(pipeType)){
                    for(HealthPipeDTO healthPipeDTO:dtos){
                        healthPipeDTO.setPipeType(serviceManager.getPipeTypeName(healthPipeDTO.getPipeType()));
                    }
                }else {
                    for(HealthPipeDTO healthPipeDTO:dtos){
                        healthPipeDTO.setPipeType(pipeType1);
                    }
                }
            }
            map.put("success", true);
            map.put("data", dtos);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("errorMsg", "返回数据异常");
        }
        return map;
    }


    @GET
    @Path("getPipeLengthAndAttachCount")
    @Produces({"application/json", "application/xml;charset=UTF-8"})
    public Map getPipeLengthAndAttachCount() throws Exception{
        Map map = new HashMap();

        try{
            List<AttachLayerDTO> dtos=serviceManager.getPipeLengthAndAttachCount();
            map.put("success", true);
            map.put("data", dtos);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("errorMsg", "返回数据异常");
        }
        return map;
    }

    @GET
    @Path("getMarkerCountByRoad")
    @Produces({"application/json", "application/xml;charset=UTF-8"})
    public Map getMarkerCountByRoad() throws Exception{
        Map map = new HashMap();

        try{
            List<FlagDTO> dtos=serviceManager.getMarkerCountByRoad();
            map.put("success", true);
            map.put("data", dtos);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("errorMsg", "返回数据异常");
        }
        return map;
    }


    @GET
    @Path("getAlarmSite")
    @Produces({"application/json", "application/xml;charset=UTF-8"})
    public Map getAlarmSite() throws Exception{
        Map map = new HashMap();
        try{
            List<AlarmSiteDTO> dtos=serviceManager.getAlarmSite();
            map.put("success", true);
            map.put("data", dtos);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("errorMsg", "返回数据异常");
        }
        return map;
    }

    private String getPipeType(String type){
        //        (0:给水管线、1:雨水管线、2:污水管线、3:燃气管线、4:热力管线)
        if("".equals(type)){
            return type;
        }
       String pipeTyep="";
        switch (Integer.parseInt(type)) {
            case 0:
                pipeTyep = "给水管线";
                break;

            case 1:
                pipeTyep = "雨水管线";

                break;

            case 2:
                pipeTyep = "污水管线";

                break;

            case 3:
                pipeTyep = "燃气管线";

                break;

            case 4:
                pipeTyep = "热力管线";

                break;

            default:
                pipeTyep = "其他";

                break;
        }
        return pipeTyep;
    }
    @Resource
   public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
}
