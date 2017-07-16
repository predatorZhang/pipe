
package com.casic.accessControl.org.web;

import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.app.manager.AppManager;
import com.casic.accessControl.auth.DTO.RoleDTO;
import com.casic.accessControl.core.ext.export.Exportor;
import com.casic.accessControl.core.ext.export.TableModel;
import com.casic.accessControl.core.ext.store.MultipartFileResource;
import com.casic.accessControl.core.ext.store.StoreConnector;
import com.casic.accessControl.core.ext.store.StoreDTO;
import com.casic.accessControl.core.mapper.JsonMapper;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.DepartmentDTO;
import com.casic.accessControl.org.dto.GroupDTO;
import com.casic.accessControl.org.dto.UserInfoDTO;
import com.casic.accessControl.org.manager.DepartmentManager;
import com.casic.accessControl.org.manager.GroupManager;
import com.casic.accessControl.org.manager.UserInfoManager;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.DataTable;
import com.casic.accessControl.util.StringUtils;
import com.casic.accessControl.xls.ExportExcel;
import com.casic.accessControl.xls.ReadColumnFromExcel;
import com.casic.accessControl.xls.ReadUserInfoFromExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * Created by Predator on 2015/6/15.
 */
@Controller
@RequestMapping("user")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private Exportor exportor;

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private DepartmentManager departmentManager;

    @Resource
    private AppManager appManager;

    @Resource
    private GroupManager groupManager;

    @Resource
    private SysLogInfoManager sysLogInfoManager;

    @Resource
    private StoreConnector storeConnector;

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }

    public void setUserInfoManager(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public void setExportor(Exportor exportor) {
        this.exportor = exportor;
    }

    public void setStoreConnector(StoreConnector storeConnector) {
        this.storeConnector = storeConnector;
    }

    @RequestMapping("validate-account")
    @ResponseBody
    public boolean validateAccount(@RequestParam(value = "username", required = true) String username) {
        if (userInfoManager.isExist(username)) {
            return false;
        }
        return true;
    }

    @RequestMapping("user-info-list")
    public void list(@RequestParam(value = "type", required = false) String type,
                     String jsonParam, HttpServletResponse response) {
        try {
            DataTable<UserInfoDTO> dt = userInfoManager.pageQueryUserInfo(jsonParam,type);
            String json = new JsonMapper().toJson(dt);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("user-info-edit")
    public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
        UserInfoDTO dto = new UserInfoDTO();
        if (null != id) {
            dto = userInfoManager.getDto(id);
        }

        List<DepartmentDTO> deps = departmentManager.findAllDepartments();
        List<AppDTO> apps = userInfoManager.findAllActiveApps(id);
        List<GroupDTO> groups = userInfoManager.findAllActiveGroups(id);
        //List<UserInfoDTO> users = userInfoManager.findAllActiveUsers();
        List<UserInfoDTO> users;
        if(null==id){
            users = userInfoManager.findAllActiveUsers();
        }else {
            users =userInfoManager.gets(id);
        }
        model.addAttribute("deps", deps);
        model.addAttribute("apps", apps);
        model.addAttribute("groups", groups);
        model.addAttribute("users", users);
        model.addAttribute("model", dto);
        return "user/user-info-edit";
    }

    @RequestMapping("user-info-change")
    public String changeUserInfo(@RequestParam(value = "id", required = false) Long id, Model model) {
        UserInfoDTO dto = new UserInfoDTO();
        if (null != id) {
            dto = userInfoManager.getDto(id);
        }
        model.addAttribute("model", dto);
        return "user/user-info-change";
    }

    @RequestMapping("user-info-batch")
    public String bathcUserInfo(){
        return "user/user-info-batch";
    }

    @RequestMapping("user-info-exp")
    public String downloadFile(HttpServletResponse response,HttpSession session) {
        try {
            userInfoManager.downloadStrategyFile(response,session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pipe/pipe-app-history";
    }


    @RequestMapping("CyberPipe-info-exp")
    public void downloadCyberPipe(HttpServletResponse response,HttpSession session) {
        try {
            userInfoManager.downloadCyberPipe(response, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("pass-new-user")
    @ResponseBody
    public Map<String, Object> passNewUser(@RequestParam(value = "id", required = true) Long id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map<String, Object> map = userInfoManager.passNewUser(id);
        return map;
    }

    @RequestMapping("user-info-save")
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute UserInfoDTO dto, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
       /*Map<String, Object> map = userInfoManager.isExist(dto, user);
        if (true == map.get("success")) {
            map = userInfoManager.saveDto(dto, user);
        }*/
        Map<String, Object> map = new HashMap<String, Object>();
        map = userInfoManager.saveDto(dto, user);
        return map;
    }

    @ResponseBody
    @RequestMapping("basic-info-save")
    public Map<String, Object> saveBasciInfo(@ModelAttribute UserInfoDTO dto, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map<String, Object> map = userInfoManager.saveBasicInfo(dto);
        return map;
    }

    @RequestMapping("user-info-reg")
    @ResponseBody
    public Map<String, Object> regUserInfo(@ModelAttribute UserInfoDTO dto) {
        Map<String, Object> map = userInfoManager.regUsrInfo(dto);
        return map;
    }

    @RequestMapping("user-info-delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "id", required = true) Long id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        return userInfoManager.removeDto(id, user);
    }

//    @RequestMapping("login")
//    @ResponseBody
//    public Map<String, Object> login(@ModelAttribute UserInfoDTO userInfoDTO, HttpSession session) {
//        return userInfoManager.login(userInfoDTO, session);
//    }

    @RequestMapping("change-password")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam(value = "newPwd", required = true) String newPwd,
                                              @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                              HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map<String, Object> map = userInfoManager.changePassowrd(oldPwd, newPwd, user);
        return map;
    }

    @RequestMapping("change-basic-info")
    @ResponseBody
    public Map<String, Object> changeContact(@RequestParam(value = "tel", required = true) String tel,
                                             @RequestParam(value = "address", required = true) String address,
                                             HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        Map<String, Object> map = userInfoManager.changeContact(tel, address, user);
        return map;
    }

    @RequestMapping("login-out")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:../index.jsp";
    }

    @RequestMapping("ill-login")
    public String illLog(@RequestParam(value = "url", required = true) String url,
                         HttpServletRequest request,HttpSession session) {
        /*String ip = getIP(request);
        SysLogInfo logInfo = new SysLogInfo();
        UserInfo user=null ;
        try{
            user= (UserInfo) session.getAttribute(StringUtils.SYS_USER);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(null!=user){
            logInfo.setUser(user);
        }
        logInfo.setLogDay(new Date());
        logInfo.setLogType(StringUtils.LOG_ALARM);
        logInfo.setMsg("客户端" + ip + "非法访问" + url);
        sysLogInfoManager.save(logInfo);*/
        return "redirect:../login.jsp";
    }

    @RequestMapping("add-user-batch")
    @ResponseBody
    public Map<String, Object> save(@RequestParam(value = "shpFile", required = true) MultipartFile file,
                                    HttpSession session) throws Exception {
        StoreDTO storeDto = storeConnector.save("xlsFile4UserInfo", new MultipartFileResource(file), file.getOriginalFilename());
        List<UserInfoDTO> lists = ReadUserInfoFromExcel.readExcel(storeDto.getResource().getFile().getPath());
        Map<String, Object> map = userInfoManager.saveUserInfoBatch(lists);
        return map;
    }

    private String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }


    @RequestMapping("login")
    public String login(ServletRequest servletRequest, Model model) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String userName = request.getUserPrincipal().getName();
        List<String> AppNameList=userInfoManager.getAppsByUserName(userName);//进入登录页获取拥有的系统
        model.addAttribute("model", AppNameList);
        return "../index";
    }
}
