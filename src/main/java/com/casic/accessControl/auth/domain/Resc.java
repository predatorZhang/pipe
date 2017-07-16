package com.casic.accessControl.auth.domain;

import com.casic.accessControl.app.domain.App;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Predator on 2015/6/16.
 */
@Entity
@Table(name = "ZX_YWGL_RESC")
@SequenceGenerator(name = "SEQ_ZX_YWGL_RESC_ID", sequenceName = "SEQ_ZX_YWGL_RESC_ID", allocationSize = 1, initialValue = 1)
public class Resc implements Serializable
{
    private Long id;

    private String name;

    private String descn;

    private App app;

    private List<Role> roles;


    private boolean active=true;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZX_YWGL_RESC_ID")
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
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="APP_ID")
    public App getApp()
    {
        return app;
    }
    public void setApp(App app)
    {
        this.app = app;
    }

    @Column(name = "ACTIVE")
    public boolean getActive()
    {
        return active;
    }
    public void setActive(boolean active)
    {
        this.active = active;
    }


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="ZX_YWGL_RESC_ROLE", joinColumns={@JoinColumn(name="RESC_ID", nullable=false, updatable=false)}, inverseJoinColumns={@JoinColumn(name="ROLE_ID", nullable=false, updatable=false)})
    public List<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

}
