package com.casic.accessControl.auth.DTO;

import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.org.domain.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/5.
 */
public class RoleDTO {

    private Long id;

    private String name;

    private String descn;

    private Long appId;

    private String appName;

    private String permIds;

    private String rescIds;

    private String permNames;

    private String rescNames;

    private String selected;

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
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPermIds() {
        return permIds;
    }

    public void setPermIds(String permIds) {
        this.permIds = permIds;
    }

    public String getPermNames() {
        return permNames;
    }

    public void setPermNames(String permNames) {
        this.permNames = permNames;
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

    public String getRescIds() {
        return rescIds;
    }

    public void setRescIds(String rescIds) {
        this.rescIds = rescIds;
    }

    public String getRescNames() {
        return rescNames;
    }

    public void setRescNames(String rescNames) {
        this.rescNames = rescNames;
    }

    public static RoleDTO ConvertToDTO(Role role) {
        if (null != role) {
            RoleDTO dto = new RoleDTO();
            dto.setId(role.getId());
            dto.setName(role.getName());
            dto.setDescn(role.getDescn());
            if (null != role.getApp() && role.getApp().getActive() == true) {
                dto.setAppId(role.getApp().getId());
                dto.setAppName(role.getApp().getName());
            }

            if (null != role.getRescs()) {
                StringBuffer buffer = new StringBuffer();
                for (Resc resc : role.getRescs()) {
                    if (resc.getActive() == true) {
                        buffer.append(resc.getName()).append(",");
                    }
                }
                if (buffer.length() > 0) {
                    buffer.deleteCharAt(buffer.length() - 1);
                    dto.setRescNames(buffer.toString());
                }
            }

            return dto;
        }
        return new RoleDTO();
    }

    public static List<RoleDTO> ConvertToDTO(List<Role> list) {
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        for (Role role : list) {
            dtos.add(ConvertToDTO(role));
        }
        return dtos;
    }
}
