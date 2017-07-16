package com.casic.accessControl.sys.dto;

import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.util.DateUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by test203 on 2015/10/23.
 */
public class ExcelInfoDTO {
    private String logType;//系统日志、运行日志、操作日志
    private String msg;
    private String logDay;
    private String userName;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLogDay() {
        return logDay;
    }

    public void setLogDay(String logDay) {
        this.logDay = logDay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static ExcelInfoDTO ConvertToDTO(SysLogInfo logInfo){
        if(null!=logInfo){
            ExcelInfoDTO dto = new ExcelInfoDTO();
            dto.setLogType(logInfo.getLogType());
            if(null!=logInfo.getLogDay()){
                dto.setLogDay(DateUtils.sdf2.format(logInfo.getLogDay()));
            }else {
                dto.setLogDay(" ");
            }
            dto.setMsg(logInfo.getMsg());
            if(null!=logInfo.getUser()){
                dto.setUserName(logInfo.getUser().getUsername());
            }else {
                dto.setUserName(" ");
            }
            return dto;
        }
        return new ExcelInfoDTO();
    }

    public static List<ExcelInfoDTO> ConvertToDTO(List<SysLogInfo> list){
        List<ExcelInfoDTO> dtoList = new ArrayList<ExcelInfoDTO>();
        for(SysLogInfo log : list){
            dtoList.add(ConvertToDTO(log));
        }
        return dtoList;
    }
}
