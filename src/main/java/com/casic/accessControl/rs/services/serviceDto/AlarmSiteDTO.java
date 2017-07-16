package com.casic.accessControl.rs.services.serviceDto;

import java.math.BigDecimal;

/**
 * Created by test203 on 2015/10/27.
 */
public class AlarmSiteDTO {
    private BigDecimal dbId;
    private String alarmType;
    private String alarmTime;
    private String region;

    public BigDecimal getDbId() {
        return dbId;
    }

    public void setDbId(BigDecimal dbId) {
        this.dbId = dbId;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
