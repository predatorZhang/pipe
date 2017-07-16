package com.casic.accessControl.org.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/6/24.
 */
public class DepNodeDTO
{

    private long id;

    private String name;

    private String code;

    private long parentId;

    private List<DepNodeDTO> children = new ArrayList<DepNodeDTO>();

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getParentId()
    {
        return parentId;
    }

    public void setParentId(long parentId)
    {
        this.parentId = parentId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<DepNodeDTO> getChildren()
    {
        return children;
    }

    public void setChildren(List<DepNodeDTO> children)
    {
        this.children = children;
    }
}
