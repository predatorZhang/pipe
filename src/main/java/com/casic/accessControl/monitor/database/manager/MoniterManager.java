package com.casic.accessControl.monitor.database.manager;

import com.casic.accessControl.core.hibernate.HibernateBasicDao;
import com.casic.accessControl.monitor.database.domain.AppUserCount;
import com.casic.accessControl.monitor.database.domain.RequestInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2016/5/13.
 */
@Service
public class MoniterManager extends HibernateBasicDao{


//    /**
//     * 更新在线人数
//     *
//     * @param serviceName 服务名称
//     * @param count 当前在线人数
//     */
//    public void updateOnlineCount(String serviceName, Integer count) {
//    }

    /**
     * 查询数据库中的所有服务的在线人数
     * @return 应用和用户数的列表
     */
    public List<AppUserCount> queryOnlineCount(){
        return null;

    }

//    /**
//     * 更新访问时间
//     * @param serviceName
//     * @param reqCount
//     * @param avgTime
//     * @param lastTime
//     */
//    public void updateRequestInfo(String serviceName,Long reqCount,Long avgTime,Long lastTime){
//
//    }

    /**
     * 获取响应时间信息
     * @return
     */
    public List<RequestInfo> queryRequestInfo(){

        return null;
    }
}
