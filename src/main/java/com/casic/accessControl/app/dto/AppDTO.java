package com.casic.accessControl.app.dto;

import com.casic.accessControl.app.domain.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/6/15.
 */
public class AppDTO {
    private Long id;

    private String name;

    private String code;

    private String descn;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
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

    public static AppDTO ConvertToDTO(App app) {
        if (null != app) {
            AppDTO dto = new AppDTO();
            dto.setId(app.getId());
            dto.setCode(app.getCode());
            dto.setDescn(app.getDescn());
            dto.setName(app.getName());
            return dto;
        }
        return new AppDTO();
    }

    public static List<AppDTO> ConvertToDTO(List<App> apps) {
        List<AppDTO> dtos = new ArrayList<AppDTO>();
        for (App app : apps) {
            dtos.add(AppDTO.ConvertToDTO(app));
        }
        return dtos;
    }
}
