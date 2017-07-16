package com.casic.accessControl.org.web;

import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.org.domain.Department;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.DepNodeDTO;
import com.casic.accessControl.org.manager.DepartmentManager;
import com.casic.accessControl.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Predator on 2015/6/15.
 */
@Controller
@RequestMapping("dep")
public class DepartmentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private DepartmentManager departmentManager;

    @Resource
    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    @RequestMapping("dep-info-list")
    @ResponseBody
    public String list() {
        List<DepNodeDTO> depNodeDTOs = new ArrayList<DepNodeDTO>();
        for (Department dep : departmentManager.getAll()) {
            if (dep.getParent() == null && dep.getStatus() == 1) {
                depNodeDTOs.add(departmentManager.getChildrenByParentId(dep.getId()));
            }
        }
        String jsonStr = null;
        try {
            jsonStr = new JsonMapper().toJson(depNodeDTOs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @RequestMapping("dep-info-save")
    @ResponseBody
    @POST
    private Map save(@ModelAttribute DepNodeDTO depNodeDTO,HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = departmentManager.saveDepartment(depNodeDTO,user);
        return map;
    }

    @RequestMapping("dep-info-del")
    @ResponseBody
    public Map delete(@RequestParam(value = "id", required = true) long id,
                      HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = departmentManager.deleteDepByID(id, user);
        return map;
    }

}
