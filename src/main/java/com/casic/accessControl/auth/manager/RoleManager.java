package com.casic.accessControl.auth.manager;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.app.manager.AppManager;
import com.casic.accessControl.auth.DTO.RoleDTO;
import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.core.page.Page;
import com.casic.accessControl.org.domain.Group;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.GroupDTO;
import com.casic.accessControl.org.manager.GroupManager;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.DataTable;
import com.casic.accessControl.util.DataTableParameter;
import com.casic.accessControl.util.DataTableUtils;
import com.casic.accessControl.util.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Predator on 2015/6/15.
 */
@Service
public class RoleManager extends HibernateEntityDao<Role> {
    @Resource
    private AppManager appManager;
    @Resource
    private GroupManager groupManager;
    @Resource
    private SysLogInfoManager sysLogInfoManager;
    @Resource
    private RescManager rescManager;

    public void setRescManager(RescManager rescManager) {
        this.rescManager = rescManager;
    }

    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(Role.class);
    }

    public DataTable<RoleDTO> pageQueryRole(String jsonParam) {
        DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);

        int start = dataTableParam.getiDisplayStart();
        int pageSize = dataTableParam.getiDisplayLength();
        int pageNo = (start / pageSize) + 1;

        Criteria criteria = getCriteria();
        criteria.addOrder(Order.desc("id"));
        criteria.add(Restrictions.eq("active", true));
        if (StringUtils.isNotBlank(dataTableParam.getsSearch())) {
            criteria.createAlias("app", "app");
            criteria.add(Restrictions.like("app.name", "%" + dataTableParam.getsSearch() + "%"));
        }

        Page page = pagedQuery(criteria, pageNo, pageSize);

        List<RoleDTO> roleDTOList = RoleDTO.ConvertToDTO((List<Role>) page.getResult());
        DataTable<RoleDTO> dt = new DataTable<RoleDTO>();
        dt.setAaData(roleDTOList);
        dt.setiTotalDisplayRecords((int) page.getTotalCount());
        dt.setsEcho(dataTableParam.getsEcho());
        dt.setiTotalRecords((int) page.getTotalCount());
        return dt;
    }

    public RoleDTO getDto(Long id) {
        return RoleDTO.ConvertToDTO(get(id));
    }

    public boolean isExist(RoleDTO dto) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.eq("name", dto.getName()));
        List<Role> list = criteria.list();

        if (list.size() > 1)//如果系统中已经存在一个以上，则肯定存在
        {
            return true;
        }
        if (list.size() == 1) {
            if (dto.getId() == null)//如果是新增的话，系统中存在一个则为已经存在
            {
                return true;
            }
            if (list.get(0).getId() != dto.getId())//如果是修改
            {
                return true;
            }
        }
        return false;
    }

    /*public Map saveDto(RoleDTO dto, UserInfo user) {
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Map map = new HashMap();
        Role role = null;
        try {
            *//*if (isExist(dto)) {
                if (null == dto.getId()) {
                    map.put("success", false);
                    map.put("message", "角色新增失败：角色名称" + dto.getName() + "已经存在！");
                } else {
                    map.put("success", false);
                    map.put("message", "角色编辑失败：角色名称" + dto.getName() + "已经存在！");
                }
                logInfo.setLogType(StringUtils.LOG_ERROR);
            } else {*//*
                if (null == dto.getId()) {
                    role = new Role();
                    role.setName(dto.getName());
                    role.setDescn(dto.getDescn());
                    if (null != dto.getAppId()) {
                        role.setApp(appManager.get(dto.getAppId()));
                    }
                    if (StringUtils.isNotBlank(dto.getPermIds())) {
                        List<Long> ids = StringUtils.ConvertToLongList(dto.getPermIds().split(","));
                        List<Perm> perms = permManager.findByIds(ids);
                        role.setPerms(perms);
                    }
                    map.put("message", "新增角色" + dto.getName() + "成功！");
                } else {
                    role = get(dto.getId());
                    role.setName(dto.getName());
                    role.setDescn(dto.getDescn());
                    if (null != dto.getAppId()) {
                        role.setApp(appManager.get(dto.getAppId()));
                    }
                    if (StringUtils.isNotBlank(dto.getPermIds())) {
                        List<Long> ids = StringUtils.ConvertToLongList(dto.getPermIds().split(","));
                        List<Perm> perms = permManager.findByIds(ids);
                        if (role.getPerms() != null) {
                            role.getPerms().clear();
                        }
                        role.setPerms(perms);
                    }
                    map.put("message", "编辑角色" + dto.getName() + "成功！");
                }
                save(role);
                map.put("success", true);
                logInfo.setLogType(StringUtils.LOG_RUN);
           *//* }*//*
        } catch (Exception e) {
            if(null==dto.getId()){
                map.put("message", "新增角色" + dto.getName() + "失败！");
            }else {
                map.put("message", "编辑角色" + dto.getName() + "失败！");
            }
            map.put("success", false);
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }*/
    public Map saveDto(RoleDTO dto, UserInfo user) {
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Map map = new HashMap();
        Role role = null;
        try {
            if (null == dto.getId()) {
                role = new Role();
                role.setName(dto.getName());
                role.setDescn(dto.getDescn());
                if (null != dto.getAppId()) {
                    role.setApp(appManager.get(dto.getAppId()));
                }
                if (StringUtils.isNotBlank(dto.getRescIds())) {
                    List<Long> ids = StringUtils.ConvertToLongList(dto.getRescIds().split(","));
                    List<Resc> rescs = rescManager.findByIds(ids);
                    role.setRescs(rescs);
                }
                map.put("message", "新增角色" + dto.getName() + "成功！");
            } else {
                role = get(dto.getId());
                role.setName(dto.getName());
                role.setDescn(dto.getDescn());
                if (null != dto.getAppId()) {
                    role.setApp(appManager.get(dto.getAppId()));
                }
                if (StringUtils.isNotBlank(dto.getRescIds())) {
                    List<Long> ids = StringUtils.ConvertToLongList(dto.getRescIds().split(","));
                    List<Resc> rescs = rescManager.findByIds(ids);
                    role.setRescs(rescs);
                }
                map.put("message", "编辑角色" + dto.getName() + "成功！");
            }
            save(role);
            map.put("success", true);
            logInfo.setLogType(StringUtils.LOG_RUN);

        } catch (Exception e) {
            if(null==dto.getId()){
                map.put("message", "新增角色" + dto.getName() + "失败！");
            }else {
                map.put("message", "编辑角色" + dto.getName() + "失败！");
            }
            map.put("success", false);
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }
    public Map removeDto(Long id, UserInfo user) {

        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Map map = new HashMap();
        Role role = get(id);
        try {
            if (role != null) {
                role.setActive(false);
                role.getRescs().clear();//删除关联表
                save(role);
                map.put("success", true);
                map.put("message", "角色" + role.getName() + "删除成功！");
                logInfo.setLogType(StringUtils.LOG_RUN);
            } else {
                map.put("success", true);
                map.put("message", "删除失败:没有找到角色" + role.getName());
                logInfo.setLogType(StringUtils.LOG_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", true);
            map.put("message", "角色" + role.getName() + "删除失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }



    public List<GroupDTO> findAllActiveGroups(Long id) {
        List<GroupDTO> dtoList = groupManager.findAllActivePosts();

        List<Long> idList = new ArrayList<Long>();
        if (null != id) {
            for (Group group : get(id).getGroups()) {
                idList.add(group.getId());
            }
        }

        if (idList.size() > 0) {
            for (GroupDTO dto : dtoList) {
                if (idList.contains(dto.getId())) {
                    dto.setSelected("selected");
                }
            }
        }

        return dtoList;
    }

    public List<RoleDTO> findAllActiveRoleDTO() {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("active", true));
        return RoleDTO.ConvertToDTO(criteria.list());
    }
}
