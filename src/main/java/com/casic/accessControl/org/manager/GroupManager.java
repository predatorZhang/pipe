package com.casic.accessControl.org.manager;

import com.casic.accessControl.auth.DTO.RoleDTO;
import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.auth.manager.RoleManager;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.core.page.Page;
import com.casic.accessControl.org.domain.Group;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.GroupDTO;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.DataTable;
import com.casic.accessControl.util.DataTableParameter;
import com.casic.accessControl.util.DataTableUtils;
import com.casic.accessControl.util.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GroupManager extends HibernateEntityDao<Group> {
    @Resource
    private RoleManager roleManager;
    @Resource
    private SysLogInfoManager sysLogInfoManager;

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(Group.class);
    }

    public DataTable<GroupDTO> queryGroupByPage(String jsonParam) {
        DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);

        int start = dataTableParam.getiDisplayStart();
        int pageSize = dataTableParam.getiDisplayLength();
        int pageNo = (start / pageSize) + 1;

        Criteria criteria = getSession().createCriteria(Group.class);
        criteria.addOrder(Order.desc("id"));
        criteria.add(Restrictions.eq("active", true));
        if (StringUtils.isNotBlank(dataTableParam.getsSearch())) {
            criteria.add(Restrictions.like("name", "%" + dataTableParam.getsSearch() + "%"));
        }

        Page page = pagedQuery(criteria, pageNo, pageSize);

        List<GroupDTO> groupDTOs = GroupDTO.ConvertToDTO((List<Group>) page.getResult());
        DataTable<GroupDTO> dt = new DataTable<GroupDTO>();
        dt.setAaData(groupDTOs);
        dt.setiTotalDisplayRecords((int) page.getTotalCount());
        dt.setsEcho(dataTableParam.getsEcho());
        dt.setiTotalRecords((int) page.getTotalCount());
        return dt;
    }

    public GroupDTO getDto(Long id) {
        return GroupDTO.ConvertToDTO(get(id));
    }

    public boolean isExist(GroupDTO dto) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.eq("name", dto.getName()));
        List<Group> list = criteria.list();

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

    public Map saveDto(GroupDTO dto, UserInfo user) {

        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if(null!=user){
            logInfo.setUser(user);
        }

        Group group = null;
        Map map = new HashMap();
        try {
            if (isExist(dto)) {
                if (null == dto.getId()) {
                    map.put("success", false);
                    map.put("message", "群组新增失败：群组名称" + dto.getName() + "已经存在！");

                } else {
                    map.put("success", false);
                    map.put("message", "群组编辑失败：群组名称" + dto.getName() + "已经存在！");
                }
            } else {
                if (null == dto.getId()) {
                    group = new Group();
                    group.setName(dto.getName());
                    group.setDescn(dto.getDescn());
                    if (StringUtils.isNotBlank(dto.getRoleIds())) {
                        List<Long> ids = StringUtils.ConvertToLongList(dto.getRoleIds().split(","));
                        List<Role> roles = roleManager.findByIds(ids);
                        group.setRoles(roles);
                    }
                    map.put("message", "新增群组" + dto.getName() + "成功！");
                } else {
                    group = get(dto.getId());
                    group.setName(dto.getName());
                    group.setDescn(dto.getDescn());
                    if (StringUtils.isNotBlank(dto.getRoleIds())) {
                        List<Long> ids = StringUtils.ConvertToLongList(dto.getRoleIds().split(","));
                        List<Role> roles = roleManager.findByIds(ids);
                        if (group.getRoles() != null) {
                            group.getRoles().clear();
                        }
                        group.setRoles(roles);
                    }
                    map.put("message", "编辑群组" + dto.getName() + "成功！");
                }
                map.put("success",true);
                logInfo.setLogType(StringUtils.LOG_RUN);
                save(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(dto.getId()==null){
                map.put("message", "新增群组" + dto.getName() + "失败！");
            }else {
                map.put("message", "编辑群组" + dto.getName() + "失败！");
            }
            map.put("success",false);
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public Map<String, Object> removeDto(Long id, UserInfo user) {
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Map map = new HashMap();
        Group group = get(id);
        try {
            if (group != null) {
                if (group.getRoles() != null) {
                    group.getRoles().clear();
                }
                group.setActive(false);
                save(group);
                map.put("success", true);
                map.put("message", "群组" + group.getName() + "删除成功！");
                logInfo.setLogType(StringUtils.LOG_RUN);
            } else {
                map.put("success", false);
                map.put("message", "群组删除失败：没有找到群组" + group.getName());
                logInfo.setLogType(StringUtils.LOG_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", true);
            map.put("message", "群组" + group.getName() + "删除失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public List<GroupDTO> findAllActiveGroups() {
        return GroupDTO.ConvertToDTO(findBy("active", true));
    }

    public List<GroupDTO> findAllActiveGroups(List<Long> ids) {
        List<GroupDTO> dtoList = GroupDTO.ConvertToDTO(findBy("active", true));
        for (GroupDTO dto : dtoList) {
            if (ids.contains(dto.getId())) {
                dto.setSelected("selected");
            }
        }
        return dtoList;
    }

    public List<GroupDTO> findAllActivePosts() {
        return GroupDTO.ConvertToDTO(findBy("active", true));
    }

    public List<RoleDTO> findAllActiveRoles(Long id) {
        List<Long> idList = new ArrayList<Long>();
        if (null != id) {
            for (Role role : get(id).getRoles()) {
                idList.add(role.getId());
            }
        }

        List<RoleDTO> dtoList = roleManager.findAllActiveRoleDTO();

        if (idList.size() > 0) {
            for (RoleDTO dto : dtoList) {
                if (idList.contains(dto.getId())) {
                    dto.setSelected("selected");
                }
            }
        }

        return dtoList;
    }
}
