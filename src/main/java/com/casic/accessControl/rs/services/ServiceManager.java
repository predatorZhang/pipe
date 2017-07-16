package com.casic.accessControl.rs.services;

import com.casic.accessControl.core.hibernate.HibernatePagingDao;
import com.casic.accessControl.rs.services.serviceDto.*;
import com.casic.accessControl.rs.services.serviceDto.FlagDTO;
import com.casic.accessControl.util.PropertiesUtil;
import com.casic.accessControl.util.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by test203 on 2015/12/18.
 */
@Service
public class ServiceManager extends HibernatePagingDao {

    protected PropertiesUtil propUtil = new PropertiesUtil("application.properties");

    public String getPipeTypeCode(String PipeName){
        if("".equals(PipeName)){
            return PipeName;
        }
        String pipeTypeCode="";
        String sql="select t.code from yj_pipe_name t where t.name like +'%"+PipeName+"%'";
        SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql);
        List<String> list=query.list();
        if(list.size()==1){
            pipeTypeCode=list.get(0);
        }else if(list.size()==0){
            pipeTypeCode="其他";
        }
        return pipeTypeCode;
    }


    public String getPipeTypeName(String PipeTypeCode){

        String PipeTypeName="";
        String sql="select t.name from yj_pipe_name t where t.code like +'%"+PipeTypeCode+"%'";
        SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql);
        List<String> list=query.list();
        if(list.size()==1){
            PipeTypeName=list.get(0);
        }else if(list.size()==0){
            PipeTypeName="其他";
        }
        return PipeTypeName;
    }


    public List<AlarmPipeDTO> getAlarmCountByPIpeTyeAndDate(String alarmDate,String pipeTye) {
        List<AlarmPipeDTO> dtos=new ArrayList<AlarmPipeDTO>();
        String sql = "select count(*) as alarmCount, d.turnx as pipeType" ;


        if("".equals(alarmDate)){
            sql += ", to_char(t.recorddate, 'yyyy') as alarmDate ";
        } else if (alarmDate.split("-").length == 1) {
                sql += ", to_char(t.recorddate, 'yyyy-mm') as alarmDate ";
            } else if ((alarmDate.split("-").length == 2)) {
                sql += ", to_char(t.recorddate, 'yyyy-mm-dd') as alarmDate ";
            } else if ((alarmDate.split("-").length == 3)) {
                sql += ", to_char(t.recorddate, 'yyyy-mm-dd hh24:mi:ss') as alarmDate ";
            }

        sql+= "   from alarm_device d " +
                "        right join alarm_alarm_record t " +
                "        on  d.devcode= t.device_code " +
                "        where d.active = 1 " +
                "        and t.active = 1 " +
                "        and d.turnx is not null";
        if (StringUtils.isNotBlank(pipeTye)) {
            sql+= " and d.turnx like '%"+pipeTye+"%'";
        }

        if("".equals(alarmDate)){
            sql += " and to_char(t.recorddate, 'yyyy')like'"+alarmDate+"%'";
        }else if(alarmDate.split("-").length==1){
                sql+= " and to_char(t.recorddate, 'yyyy-mm')like'"+alarmDate+"%'";
            }else if((alarmDate.split("-").length==2)){
                sql+= " and to_char(t.recorddate, 'yyyy-mm-dd')like'"+alarmDate+"%'";
            }else if((alarmDate.split("-").length==3)){
                sql+= " and to_char(t.recorddate, 'yyyy-mm-dd hh24:mi:ss')like'"+alarmDate+"%'";
            }

        sql+="  GROUP by  d.turnx ";

        if("".equals(alarmDate)){
            sql += ", to_char(t.recorddate, 'yyyy')";
        }else if(alarmDate.split("-").length==1){
                sql+= " , to_char(t.recorddate, 'yyyy-mm')";
            }else if((alarmDate.split("-").length==2)){
                sql+= " , to_char(t.recorddate, 'yyyy-mm-dd')";
            }else if((alarmDate.split("-").length==3)){
                sql+= " , to_char(t.recorddate, 'yyyy-mm-dd hh24:mi:ss')";
            }

        SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql)
                .addScalar("alarmCount")
                .addScalar("pipeType")
                .addScalar("alarmDate")
                .setResultTransformer(Transformers.aliasToBean(AlarmPipeDTO.class));
        dtos = query.list();

        return dtos;
    }
    public List<HealthPipeDTO> getPipeHealthCountByPIpeTyeAndDate(String alarmDate,String pipeTye) {
        List<HealthPipeDTO> dtos=new ArrayList<HealthPipeDTO>();

        String sql = "select count(*) as pipeCount, t.rank as rank,substr( t.pipeid , 4, 3) as pipeType" ;
            if("".equals(alarmDate)){
                sql += ", to_char(t.evaltime, 'yyyy') as analyseDate ";
            }else if (alarmDate.split("-").length == 1) {
                    sql += ", to_char(t.evaltime, 'yyyy-mm') as analyseDate ";
            } else if ((alarmDate.split("-").length == 2)) {
                sql += ", to_char(t.evaltime, 'yyyy-mm-dd') as analyseDate ";
            } else if ((alarmDate.split("-").length == 3)) {
                sql += ", to_char(t.evaltime,  'yyyy-mm-dd hh24:mi:ss') as analyseDate ";
            }

        sql+= "   from  yj_warning_health t " +
                " where t.EVALRECORDID in (select max(EVALRECORDID) from yj_warning_health t)";
        if (StringUtils.isNotBlank(pipeTye)) {
            sql+= " and t.pipeid like '%"+pipeTye+"%'";
        }
            if("".equals(alarmDate)){
                sql+= " and to_char(t.evaltime, 'yyyy')like'"+alarmDate+"%'";
            } else if(alarmDate.split("-").length==1){
                sql+= " and to_char(t.evaltime, 'yyyy-mm')like'"+alarmDate+"%'";
            }else if((alarmDate.split("-").length==2)){
                sql+= " and to_char(t.evaltime, 'yyyy-mm-dd')like'"+alarmDate+"%'";
            }else if((alarmDate.split("-").length==3)){
                sql+= " and to_char(t.evaltime, 'yyyy-mm-dd hh24:mi:ss')like'"+alarmDate+"%'";
            }
        sql+="  GROUP by  t.rank ,substr( t.pipeid , 4, 3)";

            if("".equals(alarmDate)){
                sql+= " , to_char(t.evaltime, 'yyyy')";
            } else if(alarmDate.split("-").length==1){
                sql+= " , to_char(t.evaltime, 'yyyy-mm')";
            }else if((alarmDate.split("-").length==2)){
                sql+= " , to_char(t.evaltime, 'yyyy-mm-dd')";
            }else if((alarmDate.split("-").length==3)){
                sql+= " , to_char(t.evaltime, 'yyyy-mm-dd hh24:mi:ss')";
            }

        SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql)
                .addScalar("pipeCount")
                .addScalar("rank")
                .addScalar("analyseDate")
                .addScalar("pipeType")
                .setResultTransformer(Transformers.aliasToBean(HealthPipeDTO.class));;
        dtos = query.list();
        for(HealthPipeDTO healthPipeDTO:dtos){
            healthPipeDTO.setRank(HealthPipeDTO.convertRank(healthPipeDTO.getRank()));
        }
        return dtos;
    }
    public List<AttachLayerDTO> getPipeLengthAndAttachCount() {
        List<AttachLayerDTO> dtos=new ArrayList<AttachLayerDTO>();
        try{
            String pipeLenth = propUtil.getProperty("ghPipeLengthData");
            String[] pipeLenthArr=pipeLenth.substring(1,pipeLenth.length()-1).split(",");
            String[] pipeType={"给水","燃气","雨水","污水","通信","电力","路灯"};
            for(int i=0;i<pipeType.length;i++){
                //double length, String pipeType, String dataJdbc
                 dtos.add(new AttachLayerDTO(Double.valueOf(pipeLenthArr[i]),pipeType[i]));
            }
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dtos;
    }
    public List<FlagDTO> getMarkerCountByRoad() {
        List<FlagDTO> dtos=new ArrayList<FlagDTO>();
        try{
            String  sql = "select count(*) as markerCount,所属道路 as Street from 标识器 GROUP by  所属道路";
            SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql)
                    .addScalar("markerCount")
                    .addScalar("Street")
                    .setResultTransformer(Transformers.aliasToBean(FlagDTO.class));
            dtos = query.list();
            return dtos;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtos;
    }

    public List<AlarmSiteDTO> getAlarmSite() {
        List<AlarmSiteDTO> dtos=new ArrayList<AlarmSiteDTO>();
        try{
            String  sql = "select t.dbid as dbId , t1.regionname as region," +
                    " to_char(t.alarm_time, 'yyyy-mm-dd hh24:mi:ss') as alarmTime," +
                    " t2.description as alarmType " +
                    " from jg_alarm_event t " +
                    "  left join jg_region t1 " +
                    "    on t.region_id = t1.dbid " +
                    "  left join jg_alarm_type t2 " +
                    "    on t.alarm_type_id = t2.dbid "+
                    "     where t.status=0";
            SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql)
                    .addScalar("dbId")
                    .addScalar("alarmType")
                    .addScalar("alarmTime")
                    .addScalar("region")
                    .setResultTransformer(Transformers.aliasToBean(AlarmSiteDTO.class));
            dtos = query.list();
            return dtos;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtos;
    }
}
