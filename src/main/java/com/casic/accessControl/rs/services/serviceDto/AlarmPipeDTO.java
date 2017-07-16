package com.casic.accessControl.rs.services.serviceDto;

import java.math.BigDecimal;

/**
 * Created by test203 on 2015/10/27.
 */
public class AlarmPipeDTO {
    private BigDecimal alarmCount;
    private String pipeType;
    private String alarmDate;
    public BigDecimal getAlarmCount() {
        return alarmCount;
    }

    public AlarmPipeDTO() {

    }
    public AlarmPipeDTO(BigDecimal alarmCount, String pipeType, String alarmDate) {
        this.alarmCount = alarmCount;
        this.pipeType = pipeType;
        this.alarmDate = alarmDate;
    }

    public void setAlarmCount(BigDecimal alarmCount) {
        this.alarmCount = alarmCount;
    }

    public String getPipeType() {
        return pipeType;
    }

    public void setPipeType(String pipeType) {
        this.pipeType = pipeType;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }
}
