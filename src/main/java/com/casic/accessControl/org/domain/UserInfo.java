package com.casic.accessControl.org.domain;

import com.casic.accessControl.app.domain.App;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2015/6/15.
 */
@Entity
@Table(name = "ZX_YWGL_USERINFO")
@SequenceGenerator(name = "SEQ_ZX_YWGL_USERINFO_ID", sequenceName = "SEQ_ZX_YWGL_USERINFO_ID", allocationSize = 1, initialValue = 1)

public class UserInfo implements Serializable
{
    private Long id;

    private String username;

    private String password;

    private String tel;

    private String address;

    private String descn;

    private Department department;

    private List<App> apps;

    private List<Group> groups;

    private Boolean passed = false;

    private Integer status = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_YWGL_USERINFO_ID")
    @Column(name = "DBID")
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name="USERNAME")
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    @Column(name="PASWORD")
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name="STATUS")
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Column(name="DESCN")
    public String getDescn()
    {
        return descn;
    }
    public void setDescn(String descn)
    {
        this.descn = descn;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DEPARTMENT_ID")
    public Department getDepartment()
    {
        return department;
    }
    public void setDepartment(Department department)
    {
        this.department = department;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ZX_YWGL_USER_APP", joinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "APP_ID", nullable = false, updatable = false)})
    public List<App> getApps()
    {
        return apps;
    }
    public void setApps(List<App> apps)
    {
        this.apps = apps;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ZX_YWGL_USER_GROUP", joinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "GROUP_ID", nullable = false, updatable = false)})
    public List<Group> getGroups()
    {
        return groups;
    }
    public void setGroups(List<Group> groups)
    {
        this.groups = groups;
    }

    @Column(name = "TEL")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    @Column(name = "ADDRESS")
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "IS_PASSED")
    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
