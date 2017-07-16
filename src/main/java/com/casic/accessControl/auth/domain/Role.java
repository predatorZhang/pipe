package com.casic.accessControl.auth.domain;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.org.domain.Group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Predator on 2015/6/16.
 */
@Entity
@Table(name = "ZX_YWGL_ROLE")
@SequenceGenerator(name = "SEQ_ZX_YWGL_ROLE_ID", sequenceName = "SEQ_ZX_YWGL_ROLE_ID", allocationSize = 1, initialValue = 1)
public class Role implements Serializable
{
    private Long id;

    private String name;

    private String descn;

    private App app;


    private List<Resc> rescs;


    private List<Group> groups;

    private Boolean active = true;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_YWGL_ROLE_ID")
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="APP_ID")
    public App getApp()
    {
        return app;
    }
    public void setApp(App app)
    {
        this.app = app;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ZX_YWGL_RESC_ROLE", joinColumns={@JoinColumn(name="ROLE_ID", nullable=false, updatable=false)}, inverseJoinColumns={@JoinColumn(name="RESC_ID", nullable=false, updatable=false)})
    public List<Resc> getRescs() {
        return rescs;
    }

    public void setRescs(List<Resc> rescs) {
        this.rescs = rescs;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ZX_YWGL_GROUP_ROLE", joinColumns={@JoinColumn(name="ROLE_ID", nullable=false, updatable=false)}, inverseJoinColumns={@JoinColumn(name="GROUP_ID", nullable=false, updatable=false)})
    public List<Group> getGroups()
    {
        return groups;
    }
    public void setGroups(List<Group> groups)
    {
        this.groups = groups;
    }

    @Column(name = "IS_ACTIVE")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
