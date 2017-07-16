package com.casic.accessControl.org.dto;

import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.org.domain.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/6/16.
 */
public class GroupDTO
{
    private Long id;

    private String name;

    private String descn;

    private String roleIds;

    private String roleNames;

    private String selected = "";

    private String btnEdit = "<a href='#' class='btn mini blue'><i class='icon-edit'></i>编辑</a>";

    private String btnDelete = "<a href='#' class='btn mini red'><i class='icon-trash'></i>删除</a>";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(String btnEdit) {
        this.btnEdit = btnEdit;
    }

    public String getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(String btnDelete) {
        this.btnDelete = btnDelete;
    }

    public static GroupDTO ConvertToDTO(Group group)
    {
        if(null!=group)
        {
            GroupDTO dto = new GroupDTO();
            dto.setId(group.getId());
            dto.setName(group.getName());
            dto.setDescn(group.getDescn());

            StringBuffer buffer = new StringBuffer("");
            for(Role role : group.getRoles())
            {
                if(role.getActive()==true){
                    buffer.append(role.getName()).append(",");
                }
            }
            if(buffer.length()>0)
            {
                buffer.deleteCharAt(buffer.length()-1);
            }
            dto.setRoleNames(buffer.toString());

            return dto;
        }
        return  new GroupDTO();
    }

    public static List<GroupDTO> ConvertToDTO(List<Group> groups)
    {
        List<GroupDTO> dtos = new ArrayList<GroupDTO>();
        for (Group group : groups)
        {
            dtos.add(ConvertToDTO(group));
        }
        return dtos;
    }
}
