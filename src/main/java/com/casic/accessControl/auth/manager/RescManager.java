package com.casic.accessControl.auth.manager;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.app.manager.AppManager;
import com.casic.accessControl.auth.DTO.RescDTO;
import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.core.page.Page;
import com.casic.accessControl.org.domain.Department;
import com.casic.accessControl.org.domain.UserInfo;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Predator on 2015/6/15.
 */
@Service
public class RescManager extends HibernateEntityDao<Resc> {
    @Resource
    private AppManager appManager;

    @Resource
    private SysLogInfoManager sysLogInfoManager;

    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(Resc.class);
    }

    public DataTable<RescDTO> pageQueryResc(String jsonParam) {
        DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);

        int start = dataTableParam.getiDisplayStart();
        int pageSize = dataTableParam.getiDisplayLength();
        int pageNo = (start / pageSize) + 1;

        Criteria criteria = getCriteria();
        criteria.createAlias("app", "a");
        criteria.addOrder(Order.desc("a.name"));
        criteria.add(Restrictions.eq("active", true));
        if (StringUtils.isNotBlank(dataTableParam.getsSearch())) {
            criteria.add(Restrictions.like("name", "%" + dataTableParam.getsSearch() + "%"));
        }

        Page page = pagedQuery(criteria, pageNo, pageSize);

        List<RescDTO> rescDTOList = RescDTO.ConvertToDTO((List<Resc>) page.getResult());
        DataTable<RescDTO> dt = new DataTable<RescDTO>();
        dt.setAaData(rescDTOList);
        dt.setiTotalDisplayRecords((int) page.getTotalCount());
        dt.setsEcho(dataTableParam.getsEcho());
        dt.setiTotalRecords((int) page.getTotalCount());
        return dt;
    }

    public RescDTO getDto(Long id) {
        return RescDTO.ConvertToDTO(get(id));
    }

    public boolean isExist(RescDTO dto) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.eq("name", dto.getName()));
        if(null!=appManager.get(dto.getAppId()));
        criteria.add(Restrictions.eq("app", appManager.get(dto.getAppId())));
        List<Resc> list = criteria.list();

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

    public Map saveDto(RescDTO dto, UserInfo user) {
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Resc resc = null;
        Map map = new HashMap();
        try {
            if (isExist(dto)) {
                if(null==dto.getId()){
                    map.put("success", false);
                    map.put("message", "资源新增失败：资源"+dto.getName()+"已经存在！");
                }else {
                    map.put("success", false);
                    map.put("message", "资源编辑失败：资源"+dto.getName()+"已经存在！");
                }
                logInfo.setLogType(StringUtils.LOG_ERROR);
            } else {
                if (null == dto.getId()) {
                    resc = new Resc();
                    resc.setName(dto.getName());
                    resc.setDescn(dto.getDescn());
                    if (null != dto.getAppId()) {
                        resc.setApp(appManager.get(dto.getAppId()));
                    }
                    map.put("message", "资源" + dto.getName() + "新增成功！");
                } else {
                    resc = get(dto.getId());
                    resc.setName(dto.getName());
                    resc.setDescn(dto.getDescn());
                    if (null != dto.getAppId()) {
                        resc.setApp(appManager.get(dto.getAppId()));
                    }
                    map.put("message", "资源" + dto.getName() + "编辑成功！");
                }
                save(resc);
                map.put("success", true);
                logInfo.setLogType(StringUtils.LOG_RUN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null == dto.getId()) {
                map.put("success", false);
                map.put("message", "资源" + dto.getName() + "新增失败！");
            } else {
                map.put("success", false);
                map.put("message", "资源" + dto.getName() + "编辑失败！");
            }
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public Map removeDto(Long id, UserInfo user) {
        Map map = new HashMap();
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        if (null != user) {
            logInfo.setUser(user);
        }

        Resc resc = get(id);
        try {
            if (resc != null) {
                resc.setActive(false);
                save(resc);
                map.put("success", true);
                map.put("message", "资源" + resc.getName() + "删除成功！");
                logInfo.setLogType(StringUtils.LOG_RUN);
            } else {
                map.put("success", false);
                map.put("message", "资源删除失败：没有找到资源" + resc.getName());
                logInfo.setLogType(StringUtils.LOG_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", true);
            map.put("message", "资源" + resc.getName() + "删除失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public List<RescDTO> findAllActiveResDto() {
        return RescDTO.ConvertToDTO(findBy("active", true));
    }
}
