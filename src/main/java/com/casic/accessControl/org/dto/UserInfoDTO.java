package com.casic.accessControl.org.dto;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.org.domain.Department;
import com.casic.accessControl.org.domain.Group;
import com.casic.accessControl.org.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/6/16.
 */
public class UserInfoDTO
{
    private Long id;

    private String username;

    private String password;

    private String tel;

    private String address;

    private String descn;

    private Long postId;

    private String postName;

    private Long depId;

    private String depName;

    private String appIds;

    private String appNames;

    private String groupIds;

    private String groupNames;

    private String selected;

    private Boolean passed;

    private Integer status;

    private String btnPass = "<a href='#' class='btn mini green'><i class='icon-edit'></i>通过</a>";

    private String btnEdit = "<a href='#' class='btn mini blue'><i class='icon-edit'></i>编辑</a>";

    private String btnDelete = "<a href='#' class='btn mini red'><i class='icon-trash'></i>删除</a>";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getAppIds() {
        return appIds;
    }

    public void setAppIds(String appIds) {
        this.appIds = appIds;
    }

    public String getAppNames() {
        return appNames;
    }

    public void setAppNames(String appNames) {
        this.appNames = appNames;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(String groupNames) {
        this.groupNames = groupNames;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getBtnPass() {
        return btnPass;
    }

    public void setBtnPass(String btnPass) {
        this.btnPass = btnPass;
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

    public static UserInfoDTO ConverToDTO(UserInfo userInfo)
    {
        if(null!=userInfo)
        {
            UserInfoDTO dto = new UserInfoDTO();
            dto.setId(userInfo.getId());
            dto.setUsername(userInfo.getUsername());
            dto.setPassword(userInfo.getPassword());
            dto.setStatus(userInfo.getStatus());
            dto.setDescn(userInfo.getDescn());
            dto.setTel(userInfo.getTel());
            dto.setAddress(userInfo.getAddress());
            dto.setPassed(userInfo.getPassed());
            if(userInfo.getPassed()) {
                dto.setBtnPass(dto.getBtnPass().replace("green","grey"));
            }
            if(userInfo.getDepartment()!=null&&userInfo.getDepartment().getStatus()==1)
            {
                dto.setDepId(userInfo.getDepartment().getId());
                dto.setDepName(userInfo.getDepartment().getName());
            }

            StringBuffer buffer = new StringBuffer("");

            List<Group> groups=userInfo.getGroups();
            if(groups.size()>0){
                for(Group group:groups){
                    List<Role> roles=group.getRoles();
                    if(roles.size()>0){
                        for(Role role:roles){
                            if(buffer.indexOf(role.getApp().getName())<0){
                                buffer.append(role.getApp().getName()).append(",");
                            }
                        }
                    }
                }
            }
//            for(App app : userInfo.getApps())
//            {
//                if(app.getActive()==true){
//                    buffer.append(app.getName()).append(",");
//                }
//            }
            if(buffer.length()>0)
            {
                buffer.deleteCharAt(buffer.length()-1);
                dto.setAppNames(buffer.toString());
                buffer.delete(0,buffer.length());
            }

            for(Group group : groups)
            {
                if(group.getActive()==true){
                    buffer.append(group.getName()).append(",");
                }
            }
            if(buffer.length()>0)
            {
                buffer.deleteCharAt(buffer.length() - 1);
            }
            dto.setGroupNames(buffer.toString());

            return dto;
        }
        return new UserInfoDTO();
    }

    public static List<UserInfoDTO> ConvertToDTO(List<UserInfo> list)
    {
        List<UserInfoDTO> dtos = new ArrayList<UserInfoDTO>();
        for(UserInfo userInfo : list)
        {
            dtos.add(ConverToDTO(userInfo));
        }
        return dtos;
    }
}
