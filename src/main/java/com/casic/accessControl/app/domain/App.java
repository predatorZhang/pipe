package com.casic.accessControl.app.domain;


import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.org.domain.UserInfo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@Table(name = "ZX_YWGL_APP")
@SequenceGenerator(name = "SEQ_ZX_YWGL_APP_ID", sequenceName = "SEQ_ZX_YWGL_APP_ID", allocationSize = 1, initialValue = 1)
public class App implements Serializable {
    private Long id;

    private String name;

    private String code;

    private String descn;

    private boolean active = true;

    private List<UserInfo> userInfos;


    private List<Resc> rescs;

    private List<Role> roles;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_YWGL_APP_ID")
    @Column(name = "DBID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "DESCN")
    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    @Column(name = "ACTIVE")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ZX_YWGL_USER_APP", joinColumns = {@JoinColumn(name = "APP_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)})
    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "app")
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Resc> getRescs() {
        return rescs;
    }

    public void setRescs(List<Resc> rescs) {
        this.rescs = rescs;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "app")
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
