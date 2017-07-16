package com.casic.accessControl.org.web;

import com.casic.accessControl.auth.DTO.RoleDTO;
import com.casic.accessControl.auth.manager.RoleManager;
import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.GroupDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Predator on 2015/6/15.
 */
@Controller
@RequestMapping("group")
public class GroupController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GroupManager groupManager;

    @Resource
    private RoleManager roleManager;

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @RequestMapping("group-info-list")
    public void list(String jsonParam, HttpServletResponse response) {
        try {
            DataTable<GroupDTO> dt = groupManager.queryGroupByPage(jsonParam);
            String json = new JsonMapper().toJson(dt);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("group-info-edit")
    public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
        GroupDTO dto = new GroupDTO();
        if (null != id) {
            dto = groupManager.getDto(id);
        }
        List<RoleDTO> roles = groupManager.findAllActiveRoles(id);
        model.addAttribute("model", dto);
        model.addAttribute("roles", roles);
        return "user/group-info-edit";
    }

    @RequestMapping("group-info-save")
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute GroupDTO dto,HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = groupManager.saveDto(dto,user);
        return map;
    }

    @RequestMapping("group-info-delete")
    @ResponseBody
    public Map delete(@RequestParam(value = "id", required = true) Long id,
                                      HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = groupManager.removeDto(id,user);
        return map;
    }
}
