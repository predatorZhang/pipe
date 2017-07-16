package com.casic.accessControl.monitor.database.web;

import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.app.manager.AppManager;
import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.util.DataTable;
import com.casic.accessControl.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Predator on 2015/6/15.
 */
@Controller
@RequestMapping("monitor")
public class MonitorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AppManager appManager;

    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    @RequestMapping("app-info-list")
    public void list(String jsonParam, HttpServletResponse response) {
        try {
            DataTable<AppDTO> dt = appManager.pageQueryAppInfo(jsonParam);
            String json = new JsonMapper().toJson(dt);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("app-info-edit")
    public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
        AppDTO dto = new AppDTO();
        if (null != id) {
            dto = appManager.getDto(id);
        }
        model.addAttribute("model", dto);
        return "user/app-info-edit";
    }

    @RequestMapping("app-info-save")
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute AppDTO dto,HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map<String, Object> map = appManager.saveDto(dto,user);
        return map;
    }

    @RequestMapping("app-info-delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Long id,
                                      HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map<String, Object> map = appManager.removeDto(id,user);
        return map;
    }

}
