package com.casic.accessControl.org.manager;

import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.org.domain.Department;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.DepNodeDTO;
import com.casic.accessControl.org.dto.DepartmentDTO;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import sun.rmi.server.UnicastServerRef;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Predator on 2015/6/15.
 */
@Service
public class DepartmentManager extends HibernateEntityDao<Department> {

    @Resource
    private SysLogInfoManager sysLogInfoManager;

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(Department.class);
    }

    public List<DepartmentDTO> findAllDepartments() {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("status", 1));
        return DepartmentDTO.ConvertDTOs(criteria.list());
    }

    public DepNodeDTO getChildrenByParentId(long id) {
        Department department = get(id);
        if (department == null) {
            return null;
        }

        //创建DepNode节点
        DepNodeDTO depNodeDTO = new DepNodeDTO();
        depNodeDTO.setId(id); //保存dbid信息
        depNodeDTO.setName(department.getName());
        depNodeDTO.setCode(department.getCode());
        if (department.getParent() != null) {
            depNodeDTO.setParentId(department.getParent().getId());
        }

        List<Department> departments = this.getActiveChildrenDeps(department);

        for (Department dep : departments) {
            depNodeDTO.getChildren().add(this.getChildrenByParentId(dep.getId()));
        }
        return depNodeDTO;
    }

    private List<Department> getActiveChildrenDeps(Department department) {
        List<Department> acDeps = new ArrayList<Department>();
        for (Department dep : department.getChildren()) {
            if (dep.getStatus() == 1) {
                acDeps.add(dep);
            }
        }
        return acDeps;
    }

    private List<UserInfo> getActiveUserByDep(Department department) {
        List<UserInfo> users = new ArrayList<UserInfo>();

        for (UserInfo user : department.getUserInfos()) {
            if (user.getStatus() == 1) {
                users.add(user);
            }
        }
        return users;

    }

    public Map saveDepartment(DepNodeDTO depNodeDTO, UserInfo user) {
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if(null!=user){
            logInfo.setUser(user);
        }

        Map map = new HashMap();
        try{
            Department department = null;
            if (depNodeDTO.getId() != 0) {
                department = get(depNodeDTO.getId());
                if (null != department) {
                    department.setName(depNodeDTO.getName());
                    department.setCode(depNodeDTO.getCode());
                }
                map.put("message", "部门" + department.getName() + "编辑成功！");
            } else {
                department = new Department();
                department.setCode(depNodeDTO.getCode());
                department.setName(depNodeDTO.getName());
                department.setParent(get(depNodeDTO.getParentId()));
                map.put("message", "部门" + department.getName() + "新增成功！");
            }
            map.put("success", true);
            logInfo.setLogType(StringUtils.LOG_RUN);
            save(department);
        }catch (Exception e){
            if(depNodeDTO.getId() != 0){
                map.put("message", "部门" + depNodeDTO.getName() + "编辑失败！");
            }else {
                map.put("message", "部门" + depNodeDTO.getName() + "新增失败！");
            }
            map.put("success", false);
            logInfo.setLogType(StringUtils.LOG_SYS);
            e.printStackTrace();
        }finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }


    public List<Department> getDepartmentList(Department department){

        List<Department> departmentList =department.getChildren();

        if(departmentList.size()>-1){
            try{
                for(Department department1:departmentList){
                    department1.setStatus(0);
                    save(department1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return departmentList;
    }

    public Map deleteDepByID(long id, UserInfo user) {

        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Map map = new HashMap();
        Department department = get(id);

        try {
            if (department != null) {

                List<Department> departmentList=getDepartmentList(department);
                if(departmentList.size()>-1){
                    for(Department department1:departmentList) {
                        getDepartmentList(department1);
                    }
                }


                department.setStatus(0);
                save(department);
                map.put("success", true);
                map.put("message", "部门" + department.getName() + "删除成功！");
                logInfo.setLogType(StringUtils.LOG_RUN);
            } else {
                map.put("success", false);
                map.put("message", "部门删除失败：没有找到部门" + department.getName());
                logInfo.setLogType(StringUtils.LOG_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "部门" + department.getName() + "删除失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

}
