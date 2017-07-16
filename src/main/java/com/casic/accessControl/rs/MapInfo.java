package com.casic.accessControl.rs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/8/26.
 */
public class MapInfo {

    private String mapUrl;

    private String updateTime;

    private String uploader;

    private String name;

    private String uuid;

    private String site;
    /*
    0:草稿
    1：待审核
    2：通过
    3: 驳回
     */
    private String status;

    //
    private int id;


    public String getMapUrl()
    {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl)
    {
        this.mapUrl = mapUrl;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUploader()
    {
        return uploader;
    }

    public void setUploader(String uploader)
    {
        this.uploader = uploader;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getSite()
    {
        return site;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public static List<MapInfo> getMockerMaps()
    {
        List<MapInfo> maps = new ArrayList<MapInfo>();
        for (int i = 0; i < 20; i++)
        {
            MapInfo map = new MapInfo();
            map.setId(i);
            map.setUploader("张帆");
            map.setMapUrl("http://192.168.0.111:9080/accessControl/logo.jpg");
            map.setSite("永旺路"+i);
            map.setUpdateTime("2015-08-02");
            map.setStatus("待审核");
            maps.add(map);
        }
        return maps;

    }
}
