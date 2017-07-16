package com.casic.accessControl.monitor.database.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lenovo on 2016/5/13.
 */

@Entity
@Table(name = "APP_REQUESTINFO")
@SequenceGenerator(name = "SEQ_ZX_APP_REQUESTINFO_ID", sequenceName = "SEQ_ZX_APP_REQUESTINFO_ID", allocationSize = 1, initialValue = 1)

public class RequestInfo {
    private Long dbId;
    private String serviceName;
    private Long reqCount;
    private Date avgReqTime;
    private Date lastReqTime;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_APP_REQUESTINFO_ID")
    @Column(name = "DBID")
    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public Long getReqCount() {
        return reqCount;
    }

    public void setReqCount(Long reqCount) {
        this.reqCount = reqCount;
    }



    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getAvgReqTime() {
        return avgReqTime;
    }

    public void setAvgReqTime(Date avgReqTime) {
        this.avgReqTime = avgReqTime;
    }

    public Date getLastReqTime() {
        return lastReqTime;
    }

    public void setLastReqTime(Date lastReqTime) {
        this.lastReqTime = lastReqTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
