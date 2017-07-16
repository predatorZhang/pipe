package com.casic.accessControl.org.manager;

import com.casic.accessControl.app.domain.App;
import com.casic.accessControl.app.dto.AppDTO;
import com.casic.accessControl.app.manager.AppManager;

import com.casic.accessControl.auth.domain.Resc;
import com.casic.accessControl.auth.domain.Role;
import com.casic.accessControl.core.ext.store.StoreConnector;
import com.casic.accessControl.core.hibernate.HibernateEntityDao;
import com.casic.accessControl.core.page.Page;
import com.casic.accessControl.core.util.IoUtils;
import com.casic.accessControl.core.util.ServletUtils;
import com.casic.accessControl.org.domain.Group;
import com.casic.accessControl.org.domain.UserInfo;
import com.casic.accessControl.org.dto.GroupDTO;
import com.casic.accessControl.org.dto.UserInfoDTO;
import com.casic.accessControl.permission.UserObj;
import com.casic.accessControl.sys.domain.SysLogInfo;
import com.casic.accessControl.sys.manager.SysLogInfoManager;
import com.casic.accessControl.util.DataTable;
import com.casic.accessControl.util.DataTableParameter;
import com.casic.accessControl.util.DataTableUtils;
import com.casic.accessControl.util.StringUtils;
import com.mysql.jdbc.log.LogUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class UserInfoManager extends HibernateEntityDao<UserInfo> {

    private static Logger logger = LoggerFactory.getLogger(UserInfoManager.class);

    @Resource
    private  UserInfoManager userInfoManager;

    @Resource
    private DepartmentManager departmentManager;
    @Resource
    private AppManager appManager;
    @Resource
    private GroupManager groupManager;
    @Resource
    private SysLogInfoManager sysLogInfoManager;



    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
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

    public void setSysLogInfoManager(SysLogInfoManager sysLogInfoManager) {
        this.sysLogInfoManager = sysLogInfoManager;
    }
    @Resource
    private StoreConnector storeConnector;

    @Resource
    public void setStoreConnector(StoreConnector storeConnector)
    {
        this.storeConnector = storeConnector;
    }
    public Criteria getCriteria() {
        return getSession().createCriteria(UserInfo.class);
    }

    public DataTable<UserInfoDTO> pageQueryUserInfo(String jsonParam,String type) {
        DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);

        int start = dataTableParam.getiDisplayStart();
        int pageSize = dataTableParam.getiDisplayLength();
        int pageNo = (start / pageSize) + 1;

        Criteria criteria = getCriteria();
        criteria.addOrder(Order.desc("id"));
        criteria.add(Restrictions.eq("status", 1));
        if("1".equals(type)){
            criteria.add(Restrictions.isNotNull("department"));
            /*criteria.add(Restrictions.isNotNull("post"));*/
        }
        if (StringUtils.isNotBlank(dataTableParam.getsSearch())) {
            criteria.add(Restrictions.like("username", "%" + dataTableParam.getsSearch() + "%"));
        }

        Page page = pagedQuery(criteria, pageNo, pageSize);

        List<UserInfoDTO> dtoList = UserInfoDTO.ConvertToDTO((List<UserInfo>) page.getResult());
        DataTable<UserInfoDTO> dt = new DataTable<UserInfoDTO>();
        dt.setAaData(dtoList);
        dt.setiTotalDisplayRecords((int) page.getTotalCount());
        dt.setsEcho(dataTableParam.getsEcho());
        dt.setiTotalRecords((int) page.getTotalCount());
        return dt;
    }

    public UserInfoDTO getDto(Long id) {
        UserInfo userInfo = get(id);
        if (null != userInfo) {
            return UserInfoDTO.ConverToDTO(userInfo);
        }
        return null;
    }



    public void downloadCyberPipe(HttpServletResponse response,HttpSession session) throws Exception {
        InputStream is = null;

        try
        {
            String filePath=session.getServletContext().getRealPath("")+"/cyberPipe/CyberPipe.rar";
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(("CyberPipe.rar").getBytes("gb2312"), "ISO8859-1") );
            is = new FileInputStream(new File(filePath));
            IoUtils.copyStream(is, response.getOutputStream());
        }finally {
            if(is != null)
            {
                is.close();
            }
        }
    }


    public void downloadStrategyFile(HttpServletResponse response,HttpSession session) throws Exception {
        InputStream is = null;

        try
        {
            String filePath=session.getServletContext().getRealPath("")+"/content/xls/users.xlsx";
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(("users.xlsx").getBytes("gb2312"), "ISO8859-1") );
            is = new FileInputStream(new File(filePath));
            IoUtils.copyStream(is, response.getOutputStream());
        }finally {
            if(is != null)
            {
                is.close();
            }
        }
    }


    public Map<String, Object> isExist(UserInfoDTO dto, UserInfo user) {

        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("status", 1));
        criteria.add(Restrictions.eq("id", dto.getId()));
        List<UserInfo> list = criteria.list();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        /*if (list.size() > 1)//如果系统中已经存在一个以上，则肯定存在
        {
            map.put("success", false);
            map.put("message", dto.getUsername() + "账户已经存在！");
        }*/
        /*if (list.size() == 1) {
            if (dto.getId() == null)//如果是新增的话，系统中存在一个则为已经存在
            {
                map.put("success", false);
                map.put("message", dto.getUsername() + "账户已经存在！");
            }
            if (list.get(0).getId() != dto.getId())//如果是修改
            {
                map.put("success", false);
                map.put("message", dto.getUsername() + "账户已经存在！");
            }
        }
        if (list.size()< 1) {
        map.put("success", true);
        }*/
        return map;
    }

    public boolean isExist(String username) {

        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("status", 1));
        criteria.add(Restrictions.eq("username", username));
        if (criteria.list().size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Map<String, Object> saveDto(UserInfoDTO dto, UserInfo user) {

        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setUser(user);

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserInfo userInfo = null;
            if (dto.getId() == null) {
                userInfo = new UserInfo();
                userInfo.setUsername(dto.getUsername());
                userInfo.setDescn(dto.getDescn());

                if (null != dto.getDepId()) {
                    userInfo.setDepartment(departmentManager.get(dto.getDepId()));
                }

                if (StringUtils.isNotBlank(dto.getAppIds())) {
                    userInfo.setApps(appManager.findByIds(StringUtils.ConvertToLongList(dto.getAppIds().split(","))));
                }

                if (StringUtils.isNotBlank(dto.getGroupIds())) {
                    userInfo.setGroups(groupManager.findByIds(StringUtils.ConvertToLongList(dto.getGroupIds().split(","))));
                }
                map.put("message", dto.getUsername() + "账户添加成功！");
            } else {
                userInfo = get(dto.getId());
                userInfo.setDescn(dto.getDescn());

                if (null != dto.getDepId()) {
                    userInfo.setDepartment(departmentManager.get(dto.getDepId()));
                }

//                if (StringUtils.isNotBlank(dto.getAppIds())) {
//                    if (null != userInfo.getApps()) {
//                        userInfo.getApps().clear();
//                    }
//                    userInfo.setApps(appManager.findByIds(StringUtils.ConvertToLongList(dto.getAppIds().split(","))));
//                }

                if (StringUtils.isNotBlank(dto.getGroupIds())) {//根据群组设置角色和系统
                    if (null != userInfo.getGroups()) {
                        userInfo.getGroups().clear();
                    }
                    List<App> appList=new ArrayList<App>();
                    List<Group> groupList=new ArrayList<Group>();
                    groupList=groupManager.findByIds(StringUtils.ConvertToLongList(dto.getGroupIds().split(",")));
                    if(groupList.size()>0){
                      for(Group group:groupList)  {
                          if(group.getRoles().size()>0){
                              for(Role role:group.getRoles()){
                                  if (!(appList.contains(role.getApp()))){
                                      appList.add(role.getApp());
                                  }
                              }
                          }
                      }
                    }
                    userInfo.setGroups(groupList);
                    userInfo.setApps(appList);
                }
                map.put("message", userInfo.getUsername() + "账户保存成功！");
            }
            map.put("success", true);
            save(userInfo);
            logInfo.setLogType(StringUtils.LOG_RUN);

        } catch (Exception e) {
            map.put("success", false);
            map.put("message", dto.getUsername() + "保存失败！");
            e.printStackTrace();
            logInfo.setLogType(StringUtils.LOG_RUN);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    @Transactional
    public Map<String, Object> removeDto(Long id, UserInfo user) {

        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setUser(user);

        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = get(id);
        try {
            if (userInfo != null) {
                userInfo.setStatus(0);
                save(userInfo);
                map.put("success", true);
                map.put("message", userInfo.getUsername() + "删除成功！");
            } else {
                map.put("success", false);
                map.put("message", "删除失败:没有找到" + userInfo.getUsername() + "！");
            }
            logInfo.setLogType(StringUtils.LOG_RUN);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "删除失败" + userInfo.getUsername() + "！");
            logInfo.setLogType(StringUtils.LOG_ERROR);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            logInfo.setUser(user);
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public List<AppDTO> findAllActiveApps(Long id) {
        List<Long> idList = new ArrayList<Long>();
        if (null != id) {
            for (App app : get(id).getApps()) {
                idList.add(app.getId());
            }
        }

        List<AppDTO> dtoList = appManager.findAllActiveAppDto();

        if (idList.size() > 0) {
            for (AppDTO dto : dtoList) {
                if (idList.contains(dto.getId())) {
                    dto.setSelected("selected");
                }
            }
        }

        return dtoList;
    }

    public List<GroupDTO> findAllActiveGroups(Long id) {
        List<Long> idList = new ArrayList<Long>();
        if (null != id) {
            for (Group group : get(id).getGroups()) {
                idList.add(group.getId());
            }
        }

        List<GroupDTO> dtoList = groupManager.findAllActiveGroups();

        if (idList.size() > 0) {
            for (GroupDTO dto : dtoList) {
                if (idList.contains(dto.getId())) {
                    dto.setSelected("selected");
                }
            }
        }

        return dtoList;
    }

    @Transactional
    public Map<String, Object> login(UserInfoDTO dto, HttpSession session) {
        SysLogInfo logInfo = new SysLogInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("username", dto.getUsername()));
            criteria.add(Restrictions.eq("password", dto.getPassword()));
            criteria.add(Restrictions.eq("status", 1));
            criteria.add(Restrictions.eq("passed", true));
            UserInfo userInfo = (UserInfo) criteria.setMaxResults(1).uniqueResult();
            if (userInfo != null) {
                session.setAttribute(StringUtils.SYS_USER, userInfo);
                ServletContext application = session.getServletContext();
                HashSet sessions = (HashSet)application.getAttribute("sessions");
                if(sessions == null){
                    sessions = new HashSet();
                    application.setAttribute("sessions",sessions);
                }
                sessions.add(session);
                application.setAttribute("sessions", sessions);
                map.put("success", true);
                map.put("message", dto.getUsername() + "登录系统！");
                logInfo.setLogType(StringUtils.LOG_RUN);
                logInfo.setUser(userInfo);

                UserObj userObj=validateUser(dto.getUsername(), dto.getPassword(), "4");//设置权限用
                session.setAttribute("sys_login_person", userObj);
                map.put("user", userObj);//控制权限使用
            } else {
                map.put("success", false);
                map.put("message", dto.getUsername() + "登录失败！");
                logInfo.setLogType(StringUtils.LOG_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", dto.getUsername() + "登录失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }


    public List<String> getAppsByUserName(String userName){
        List<String> stringList =new ArrayList<String>();

        try{
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("username", userName));
            criteria.add(Restrictions.eq("status", 1));
            UserInfo userInfo = (UserInfo) criteria.uniqueResult();
            List<Group> groups=userInfo.getGroups();
            if(groups.size()>0){
                for(Group group:groups){
                    List<Role> roles=group.getRoles();
                    if(roles.size()>0){
                        for(Role role:roles){
                            if(!(stringList.contains(role.getApp().getName()))){
                                stringList.add(role.getApp().getName());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  stringList;
    }

    public List<App> getAppsByUser(UserInfo userInfo){
        List apps=new ArrayList<App>();
        try {
            List<Group> groups=userInfo.getGroups();
            if(groups.size()>0){
                for(Group group:groups){
                    List<Role> roles=group.getRoles();
                    if(roles.size()>0){
                        for(Role role:roles){
                            if(!(apps.contains(role.getApp()))){
                                apps.add(role.getApp());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return apps;
    }



    public UserObj validateUser(String username, String password, String appId) {

        if (!StringUtils.isNotBlank(appId)) {
            logger.warn("appId cannot be null");
        }

        UserObj userObj = new UserObj();
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("username", username));
//        criteria.add(Restrictions.eq("password", password));
        criteria.add(Restrictions.eq("status", 1));
        UserInfo userInfo = (UserInfo) criteria.uniqueResult();
        App app = appManager.get(Long.parseLong(appId));

        if (null != userInfo) {

            for (App a : getAppsByUser(userInfo)) {
                if (a.getId() == Long.parseLong(appId) && a.getActive() == true) {

                    List<String> rescs = new ArrayList<String>();
                    StringBuffer buff = new StringBuffer();

                    for (Group group : userInfo.getGroups()) {
                        for (Role role : group.getRoles()) {
                            if(role.getApp()==app){
                                for (Resc resc : role.getRescs()) {
                                    rescs.add(resc.getName());
                                }
                            }
                        }
                    }

                    for (String resc : rescs) {
                        buff.append("app." + appId + ":" + resc).append(",");
                    }

                    buff.deleteCharAt(buff.length() - 1);
                    String authorities = buff.toString();
                    userObj.setAuthorities(authorities);
                    userObj.setUserName(username);
                    userObj.setAppId(appId.toString());
                    userObj.setPassword(password);
                    break;
                }
            }
        }
        return userObj;
    }

    public Map<String, Object> regUsrInfo(UserInfoDTO dto) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(dto.getUsername());
            userInfo.setTel(dto.getTel());
            userInfo.setAddress(dto.getAddress());
            userInfo.setPassed(false);
            userInfo.setPassword(dto.getPassword());
            userInfo.setDescn(dto.getDescn());
            save(userInfo);
            map.put("success", true);
            map.put("message", dto.getUsername() + "用户注册成功！");

            logInfo.setLogType(StringUtils.LOG_RUN);
            logInfo.setUser(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", dto.getUsername() + "用户注册失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public Map<String, Object> changePassowrd(String oldPwd, String newPwd, UserInfo user) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setLogDay(new Date());
        logInfo.setUser(user);
        try {
            UserInfo userInfo = get(user.getId());
            if (null == userInfo) {
                map.put("success", false);
                map.put("message", "密码修改错误：没有找到用户" + user.getUsername());
                logInfo.setLogType(StringUtils.LOG_ERROR);
            } else if (!userInfo.getPassword().equals(oldPwd)) {
                map.put("success", false);
                map.put("message", "密码修改错误：原始密码有误！");
                logInfo.setLogType(StringUtils.LOG_ERROR);
            } else {
                userInfo.setPassword(newPwd);
                save(userInfo);
                map.put("success", true);
                map.put("message", user.getUsername() + "修改密码成功！");
                logInfo.setLogType(StringUtils.LOG_RUN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", user.getUsername() + "修改密码失败！");
            logInfo.setLogType(StringUtils.LOG_SYS);
        } finally {
            logInfo.setMsg((String) map.get("message"));
            sysLogInfoManager.save(logInfo);
        }
        return map;
    }

    public List<UserInfoDTO> findAllActiveUsers() {
        List<UserInfoDTO> dtos = new ArrayList<UserInfoDTO>();
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("status", 1));
        criteria.add(Restrictions.isEmpty("apps"));
        criteria.add(Restrictions.isEmpty("groups"));
        dtos = UserInfoDTO.ConvertToDTO(criteria.list());
        return dtos;
    }

    public Map<String, Object> changeContact(String tel, String address, UserInfo user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            user.setAddress(address);
            user.setTel(tel);
            save(user);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改失败！");
        }
        return map;
    }

    public Map<String, Object> saveBasicInfo(UserInfoDTO dto) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserInfo userInfo = null;
            if (null != dto.getId()) {
                userInfo = get(dto.getId());
            } else {
                userInfo=userInfoManager.findUniqueBy("username", dto.getUsername());
                if(null!=userInfo){
                    if(1==userInfo.getStatus()){
                        map.put("success", false);
                        map.put("message", "添加失败，账户名重复！");
                        return map;
                    }else {
                        userInfo.setPassed(true);
                        if(StringUtils.isBlank(dto.getPassword())){
                            userInfo.setPassword(dto.getUsername()+"123");
                        }else{
                            userInfo.setPassword(dto.getPassword());
                        }
                        userInfo.setTel(dto.getTel());
                        userInfo.setStatus(1);
                        userInfo.setAddress(dto.getAddress());
                        userInfo.setDescn(dto.getDescn());
                        save(userInfo);
                        map.put("success", true);
                        return map;
                    }
                }
                    userInfo = new UserInfo();
                    userInfo.setUsername(dto.getUsername());
//                userInfo.setPassword("111111");
                    userInfo.setPassed(true);


            }
            if(StringUtils.isBlank(dto.getPassword())){
                userInfo.setPassword(dto.getUsername()+"123");
            }else{
                userInfo.setPassword(dto.getPassword());
            }
            userInfo.setTel(dto.getTel());
            userInfo.setAddress(dto.getAddress());
            userInfo.setDescn(dto.getDescn());
            save(userInfo);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改失败！");
        }
        return map;
    }

    public Map<String, Object> passNewUser(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserInfo userInfo = get(id);
            if (null != userInfo) {
                userInfo.setPassed(true);
                save(userInfo);
            }
            map.put("success", true);
            map.put("message", "审核成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "审核失败！");
        }
        return map;
    }

    @Transactional
    public Map<String, Object> saveUserInfoBatch(List<UserInfoDTO> lists) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            for (UserInfoDTO dto : lists) {
                if (isExist(dto.getUsername())) {
                    map.put("success", false);
                    map.put("message", "导入失败:账号\"" + dto.getUsername() + "\"已经存在！");
                    break;
                }
            }
            if (!map.containsKey("success")) {
                for (UserInfoDTO dto : lists) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUsername(dto.getUsername());
                    userInfo.setTel(dto.getTel());
                    userInfo.setAddress(dto.getAddress());
                    userInfo.setDescn(dto.getDescn());
                    userInfo.setPassword(dto.getPassword());
                    userInfo.setPassed(dto.getPassed());
                    save(userInfo);
                }
                map.put("success", true);
                map.put("message", "批量导入成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "批量导入失败！");
        }
        return map;
    }

    public List<UserInfoDTO> gets(Long id) {
        List<UserInfoDTO> dtos = new ArrayList<UserInfoDTO>();
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("status", 1));
        criteria.add(Restrictions.eq("id", id));
        dtos = UserInfoDTO.ConvertToDTO(criteria.list());
        return dtos;

    }
}
