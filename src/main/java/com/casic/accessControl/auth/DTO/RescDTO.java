package com.casic.accessControl.auth.DTO;

import com.casic.accessControl.auth.domain.Resc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/6/16.
 */
public class RescDTO {
    private Long id;

    private String name;

    private long appId;

    private String appName;

    private String descn;

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

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
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


    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public static RescDTO ConvertToDTO(Resc resc) {
        RescDTO dto = new RescDTO();
        if (null != resc) {
            dto.setId(resc.getId());
            dto.setName(resc.getName());
            dto.setDescn(resc.getDescn());
            if (null != resc.getApp() && resc.getApp().getActive() == true) {
                dto.setAppId(resc.getApp().getId());
                dto.setAppName(resc.getApp().getName());
            }
        }
        return dto;
    }

    public static List<RescDTO> ConvertToDTO(List<Resc> list) {
        List<RescDTO> dtos = new ArrayList<RescDTO>();
        for (Resc resc : list) {
            if(resc.getActive()==true)
            dtos.add(ConvertToDTO(resc));
        }
        return dtos;
    }
}
