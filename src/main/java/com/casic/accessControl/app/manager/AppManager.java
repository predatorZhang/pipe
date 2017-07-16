package com.casic.accessControl.app.manager;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.auth.DTO.RescDTO;
import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.auth.manager.RoleManager;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.core.page.Page;
import com.casic.accessControl.org.domain.UserInfo;
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

@Service
public class AppManager extends HibernateEntityDao<App> {

    private SysLogInfoManager sysLogInfoManager;
    private RoleManager roleManager;

    @Resource
    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    @Resource
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(App.class);
    }

    public DataTable<AppDTO> pageQueryAppInfo(String jsonParam) {
        DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);

        int start = dataTableParam.getiDisplayStart();
        int pageSize = dataTableParam.getiDisplayLength();
        int pageNo = (start / pageSize) + 1;

        Criteria criteria = getCriteria();
        criteria.addOrder(Order.desc("id"));
        criteria.add(Restrictions.eq("active", true));

        if (StringUtils.isNotBlank(dataTableParam.getsSearch())) {
            criteria.add(Restrictions.like("name", "%" + dataTableParam.getsSearch() + "%"));
        }

        Page page = pagedQuery(criteria, pageNo, pageSize);

        List<AppDTO> dtoList = AppDTO.ConvertToDTO((List<App>) page.getResult());
        DataTable<AppDTO> dt = new DataTable<AppDTO>();
        dt.setAaData(dtoList);
        dt.setiTotalDisplayRecords((int) page.getTotalCount());
        dt.setsEcho(dataTableParam.getsEcho());
        dt.setiTotalRecords((int) page.getTotalCount());
        return dt;
    }

    public AppDTO getDto(Long id) {
        App app = get(id);
        if (null != app) {
            return AppDTO.ConvertToDTO(app);
        }
        return null;
    }

    public boolean isExist(AppDTO dto) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.eq("code", dto.getCode()));
        List<App> list = criteria.list();

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

    public Map<String, Object> saveDto(AppDTO dto, UserInfo user) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        logInfo.setUser(user);
        try {
            if (isExist(dto)) {
                map.put("success", false);
                logInfo.setLogType(StringUtils.LOG_ERROR);
                if(dto.getId()==null){
                    map.put("message", "应用"+dto.getName()+"新增失败：应用代码" + dto.getCode() + "已经存在！");
                }else {
                    map.put("message", "应用"+dto.getName()+"编辑失败：应用代码" + dto.getCode() + "已经存在！");
                }
            } else {
                App app = null;
                if (dto.getId() == null) {
                    app = new App();
                    app.setCode(dto.getCode());
                    app.setName(dto.getName());
                    app.setDescn(dto.getDescn());
                    save(app);
                    map.put("success", true);
                    map.put("message", "应用"+dto.getName()+"添加成功！");
                    logInfo.setLogType(StringUtils.LOG_RUN);
                } else {
                    app = get(dto.getId());
                    app.setCode(dto.getCode());
                    app.setName(dto.getName());
                    app.setDescn(dto.getDescn());
                    save(app);
                    map.put("success", true);
                    map.put("message", "应用"+dto.getName()+"编辑成功！");
                    logInfo.setLogType(StringUtils.LOG_RUN);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            if(dto.getId()==null){
                map.put("message", "应用"+dto.getName()+"新增失败！");
            }else {
                map.put("message", "应用"+dto.getName()+"编辑失败！");
            }
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public Map<String, Object> removeDto(Long id, UserInfo user) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setUser(user);
        logInfo.setLogDay(new Date());
        App app = get(id);
        try {
            if (app != null) {
                if (null != app.getUserInfos()) {
                    app.getUserInfos().clear();
                }
                app.setActive(false);
                save(app);
                map.put("success", true);
                map.put("message", "应用" + app.getName() + "删除成功！");
                logInfo.setLogType(StringUtils.LOG_RUN);
            } else {
                map.put("success", false);
                map.put("message", "删除失败：没有找到应用" + app.getName());
                logInfo.setLogType(StringUtils.LOG_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "应用" + app.getName() + "删除失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
        }
        return map;
    }

    public List<AppDTO> findAllActiveAppDto() {
        return AppDTO.ConvertToDTO(findBy("active", true));
    }
    public List<RescDTO> findAllActiveRescs(Long id,String appName) {
        List<Long> idList = new ArrayList<Long>();
        List<RescDTO> dtoList;
        dtoList=RescDTO.ConvertToDTO(this.findUniqueBy("name",appName).getRescs());
        if(null!=id){
            for (Resc resc : roleManager.get(id).getRescs()) {
                if(resc.getActive()==true)
                idList.add(resc.getId());
            }
        }
        if (idList.size() > 0) {
            for (RescDTO dto : dtoList) {
                if (idList.contains( dto.getId().longValue())) {
                    dto.setSelected("selected");
                }
            }
        }

        return dtoList;
    }
}
