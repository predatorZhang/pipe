package com.casic.accessControl.sys.manager;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.core.page.Page;
import com.casic.accessControl.org.dto.DepartmentDTO;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.dto.ExcelInfoDTO;
import com.casic.accessControl.sys.dto.SysLogInfoDTO;
import com.casic.accessControl.util.*;
import com.casic.accessControl.xls.ExportExcel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service
public class SysLogInfoManager extends HibernateEntityDao<SysLogInfo> {

    public Criteria getCriteria() {
        return getSession().createCriteria(SysLogInfo.class);
    }

    public DataTable<SysLogInfoDTO> pageQueryLogInfoDTO(String jsonParam, String logType, String beginDay, String endDay) throws ParseException {
        DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);

        int start = dataTableParam.getiDisplayStart();
        int pageSize = dataTableParam.getiDisplayLength();
        int pageNo = (start / pageSize) + 1;

        Criteria criteria = getCriteria();
        criteria.addOrder(Order.desc("id"));

        if (StringUtils.isNotBlank(logType)) {
            criteria.add(Restrictions.like("logType", "%" + logType + "%"));
        }
        if (StringUtils.isNotBlank(beginDay)) {
            criteria.add(Restrictions.ge("logDay", DateUtils.sdf1.parse(beginDay)));
        }
        if (StringUtils.isNotBlank(endDay)) {
            Date date = DateUtils.sdf1.parse(endDay);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            criteria.add(Restrictions.le("logDay", calendar.getTime()));
        }

        Page page = pagedQuery(criteria, pageNo, pageSize);

        List<SysLogInfoDTO> dtoList = SysLogInfoDTO.ConvertToDTO((List<SysLogInfo>) page.getResult());
        DataTable<SysLogInfoDTO> dt = new DataTable<SysLogInfoDTO>();
        dt.setAaData(dtoList);
        dt.setiTotalDisplayRecords((int) page.getTotalCount());
        dt.setsEcho(dataTableParam.getsEcho());
        dt.setiTotalRecords((int) page.getTotalCount());
        return dt;
    }

    public SysLogInfoDTO getDTO(Long id) {
        return SysLogInfoDTO.ConvertToDTO(get(id));
    }

    public Map<String, Object> expSysLogToExcel(String logType, String beginDay, String endDay, String path) throws ParseException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        Criteria criteria = getCriteria();
        if (StringUtils.isNotBlank(logType)) {
            criteria.add(Restrictions.like("logType", "%" + logType + "%"));
        }
        if (StringUtils.isNotBlank(beginDay)) {
            criteria.add(Restrictions.ge("logDay", DateUtils.sdf1.parse(beginDay)));
        }
        if (StringUtils.isNotBlank(endDay)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.sdf1.parse(endDay));
            calendar.add(Calendar.DATE, 1);
            criteria.add(Restrictions.le("logDay", calendar.getTime()));
        }
        List<ExcelInfoDTO> dtoList = ExcelInfoDTO.ConvertToDTO(criteria.list());
        String[] headers = {"日志类型", "日志内容", "日期", "操作人"};
        OutputStream out = new FileOutputStream(path);
        ExportExcel<ExcelInfoDTO> ex = new ExportExcel<ExcelInfoDTO>();
        ex.exportExcel(headers, dtoList, out);
        map.put("success", true);
        return map;
    }
}
