package com.casic.accessControl.org.domain;

import com.casic.accessControl.auth.domain.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Predator on 2015/6/15.
 */
@Entity
@Table(name = "ZX_YWGL_GROUP")
@SequenceGenerator(name = "SEQ_ZX_YWGL_GROUP_ID", sequenceName = "SEQ_ZX_YWGL_GROUP_ID", allocationSize = 1, initialValue = 1)
public class Group implements Serializable
{
    private Long id;

    private String name;

    private String descn;

    private List<UserInfo> userInfos;

    private List<Role> roles;

    private Boolean active = true;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_YWGL_GROUP_ID")
    @Column(name = "DBID")

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "DESCN")
    public String getDescn()
    {
        return descn;
    }

    public void setDescn(String descn)
    {
        this.descn = descn;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ZX_YWGL_USER_GROUP", joinColumns = {@JoinColumn(name = "GROUP_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)})
    public List<UserInfo> getUserInfos()
    {
        return userInfos;
    }
    public void setUserInfos(List<UserInfo> userInfos)
    {
        this.userInfos = userInfos;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ZX_YWGL_GROUP_ROLE", joinColumns={@JoinColumn(name="GROUP_ID", nullable=false, updatable=false)}, inverseJoinColumns={@JoinColumn(name="ROLE_ID", nullable=false, updatable=false)})
    public List<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    @Column(name = "IS_ACTIVE",nullable=false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
