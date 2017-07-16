package com.casic.accessControl.auth.web;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.app.manager.AppManager;
import com.casic.accessControl.auth.DTO.RescDTO;
import com.casic.accessControl.auth.DTO.RoleDTO;
import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.auth.manager.RoleManager;
import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.manager.GroupManager;
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
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Predator on 2015/6/15.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RoleManager roleManager;
    @Resource
    private AppManager appManager;

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    @RequestMapping("role-info-list")
    public void list(String jsonParam, HttpServletResponse response) {
        try {
            DataTable<RoleDTO> dt = roleManager.pageQueryRole(jsonParam);
            String json = new JsonMapper().toJson(dt);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("get-app-rescs")
    @ResponseBody
    public Map<String, Object>  getRescs(@RequestParam(value = "appId", required = true) Long appId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<RescDTO> rescs=new ArrayList<RescDTO>();
        try{
            if (null != appId) {
                rescs = appManager.findAllActiveRescs(null,appManager.get(appId).getName());
                map.put("success", true);
                map.put("rescs",rescs);
            }else {
                map.put("success", false);
                map.put("rescs",rescs);
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("rescs", rescs);
        }

        return map;
    }

    @RequestMapping("role-info-edit")
    public String edit(@RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "appName", required = false) String appName,
                       Model model) throws Exception{
        RoleDTO dto = new RoleDTO();
        App app =new App();
        if (null != id) {
            dto = roleManager.getDto(id);
        }

        List<App> apps = appManager.findBy("active", true);

        if(null==appName) {
            appName = apps.get(0).getName();
        }else {
            appName= URLDecoder.decode(appName, "UTF-8");
        }
//        List<PermDTO> perms = roleManager.findAllActivePerms(id,appName);
        List<RescDTO> rescs = appManager.findAllActiveRescs(id,appName);
        model.addAttribute("apps", apps);
//        model.addAttribute("perms", perms);
        model.addAttribute("rescs", rescs);
        model.addAttribute("model", dto);
        return "auth/role-info-edit";
    }

    @RequestMapping("role-info-save")
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute RoleDTO dto, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = roleManager.saveDto(dto,user);
        return map;
    }

    @RequestMapping("role-info-delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Long id,
                                      HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = roleManager.removeDto(id, user);
        return map;
    }
}
