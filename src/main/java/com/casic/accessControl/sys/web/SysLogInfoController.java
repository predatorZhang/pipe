package com.casic.accessControl.sys.web;

import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.core.ext.mail.MailConsumer;
import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.org.dto.DepartmentDTO;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.dto.SysLogInfoDTO;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.DataTable;
import com.casic.accessControl.xls.ExportExcel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
@RequestMapping("sys")
public class SysLogInfoController {

    @Resource
    private SysLogInfoManager sysLogInfoManager;

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    @RequestMapping("log-info-list")
    public void list(String jsonParam
            , @RequestParam(value = "logType", required = false) String logType
            , @RequestParam(value = "beginDay", required = false) String beginDay
            , @RequestParam(value = "endDay", required = false) String endDay
            , HttpServletResponse response) {
        try {
            DataTable<SysLogInfoDTO> dt = sysLogInfoManager.pageQueryLogInfoDTO(jsonParam, logType, beginDay, endDay);
            String json = new JsonMapper().toJson(dt);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("sys-log-msg")
    public String logMsg(@RequestParam(value = "id", required = true) Long id, Model model) {
        SysLogInfoDTO dto = sysLogInfoManager.getDTO(id);
        model.addAttribute("model", dto);
        return "sys/sys-log-msg";
    }

    @RequestMapping("exp-sys-log")
    @ResponseBody
    public Map<String, Object> expSysLog(@RequestParam(value = "logType", required = false) String logType,
                                         @RequestParam(value = "beginDay", required = false) String beginDay,
                                         @RequestParam(value = "endDay", required = false) String endDay,
                                         HttpServletResponse response,
                                         HttpServletRequest request) throws IOException {
        Map<String, Object> map = null;
        String logType1="";
        if(""!=(logType)&&null!=logType){
            logType1=URLDecoder.decode(logType, "UTF-8");
        }
        try {
            String path = request.getSession().getServletContext().getRealPath("/") + "\\content\\xls\\log.xls";
            map = sysLogInfoManager.expSysLogToExcel(logType1, beginDay, endDay, path);
        } catch (Exception e) {
            e.printStackTrace();
            map = new HashMap<String, Object>();
            map.put("success", false);
            map.put("message", "Excel文件生成失败！");
        }
        return map;
    }
}
