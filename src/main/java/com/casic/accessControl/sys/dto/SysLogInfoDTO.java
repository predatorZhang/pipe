package com.casic.accessControl.sys.dto;

import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SysLogInfoDTO {
    private Long id;
    private String logType;//系统日志、运行日志、操作日志
    private String msg;
    private String logDay;
    private Long userId;
    private String userName;
    private String btn = "<a href='#' class='btn mini blue'>查看</a>";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public static SysLogInfoDTO ConvertToDTO(SysLogInfo log){
        if(null!=log){
            SysLogInfoDTO dto = new SysLogInfoDTO();
            dto.setId(log.getId());
            dto.setLogType(log.getLogType());
            if(null!=log.getLogDay()){
                dto.setLogDay(DateUtils.sdf2.format(log.getLogDay()));
            }
            dto.setMsg(log.getMsg());
            if(null!=log.getUser()){
                dto.setUserId(log.getUser().getId());
                dto.setUserName(log.getUser().getUsername());
            }
            return dto;
        }
        return new SysLogInfoDTO();
    }

    public static List<SysLogInfoDTO> ConvertToDTO(List<SysLogInfo> list){
        List<SysLogInfoDTO> dtoList = new ArrayList<SysLogInfoDTO>();
        for(SysLogInfo log : list){
            dtoList.add(ConvertToDTO(log));
        }
        return dtoList;
    }
}
