package com.casic.accessControl.auth.web;

import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.app.manager.AppManager;
import com.casic.accessControl.auth.DTO.RescDTO;
import com.casic.accessControl.auth.manager.RescManager;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Predator on 2015/6/15.
 */
@Controller
@RequestMapping("resc")
public class RescController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource private RescManager rescManager;

    @Resource private AppManager appManager;

    public void setRescManager(RescManager rescManager) {
        this.rescManager = rescManager;
    }

    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    @RequestMapping("resc-info-list")
    public void list(String jsonParam , HttpServletResponse response) {
        try
        {
            DataTable<RescDTO> dt = rescManager.pageQueryResc(jsonParam);
            String json = new JsonMapper().toJson(dt);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RequestMapping("resc-info-edit")
    public String edit(@RequestParam(value = "id",required = false)Long id,Model model) {
        RescDTO dto = new RescDTO();
        if(null!=id)
        {
            dto = rescManager.getDto(id);
        }
        List<AppDTO> appDTOList = appManager.findAllActiveAppDto();
        model.addAttribute("model", dto);
        model.addAttribute("apps",appDTOList);
        return "auth/resc-info-edit";
    }

    @RequestMapping("resc-info-save")
    @ResponseBody
    public Map<String,Object> save(@ModelAttribute RescDTO dto,HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = rescManager.saveDto(dto, user);
        return map;
    }

    @RequestMapping("resc-info-delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam(value = "id",required = true) Long id,
                                     HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map map = rescManager.removeDto(id,user);;
        return map;
    }
}
