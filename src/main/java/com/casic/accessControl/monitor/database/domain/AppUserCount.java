package com.casic.accessControl.monitor.database.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lenovo on 2016/5/13.
 */
@Entity
@Table(name = "APP_USER_COUNT")
@SequenceGenerator(name = "SEQ_ZX_APP_USER_COUNT_ID", sequenceName = "SEQ_ZX_APP_USER_COUNT_ID", allocationSize = 1, initialValue = 1)
public class AppUserCount {
    private String serviceName;
    private Integer onlineCount;
    private Long dbId;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_APP_USER_COUNT_ID")
    @Column(name = "DBID")
    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }
    @Column(name="SERVICE_NAME")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Column(name="ONLINE_COUNT")
    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    @Column(name="UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
