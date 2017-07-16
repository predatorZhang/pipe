package com.casic.accessControl.sys.domain;

import com.casic.accessControl.org.domain.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/8/17.
 */
@Entity
@Table(name = "ZX_SYS_LOG_INFO")
@SequenceGenerator(name = "SEQ_ZX_SYS_LOG_ID",sequenceName = "SEQ_ZX_SYS_LOG_ID",allocationSize = 1,initialValue = 1)
public class SysLogInfo implements Serializable{
    private Long id;
    private String logType;//系统日志、运行日志、操作日志
    private String msg;
    private Date logDay = new Date();
    private UserInfo user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ZX_SYS_LOG_ID")
    @Column(name = "DBID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LOG_TYPE",nullable = false)
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Column(name = "LOG_MSG",nullable = false)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Column(name = "LOG_DAY")
    public Date getLogDay() {
        return logDay;
    }

    public void setLogDay(Date logDay) {
        this.logDay = logDay;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
