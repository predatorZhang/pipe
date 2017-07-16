package com.casic.accessControl.rs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/1/15.
 */
@Component
@Path("map")
public class MapResource
{
    private static Logger logger = LoggerFactory.getLogger(MapResource.class);

    private static List<MapInfo> maps = MapInfo.getMockerMaps();
    @POST
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Map get(@FormParam("userId") String userId,
                          @FormParam("lastId") String lastId,
                          @FormParam("sort") String sort) {
        Map map = new HashMap<String, Object>();
        int oldId = Integer.parseInt(lastId);
        int size = maps.size();
        List<MapInfo> result = new ArrayList<MapInfo>();
        //只根据lastId来进行查询操作
        if (oldId > size)
        {
            oldId = 0;
        }
        else
        {
            oldId++;
        }
        for (int i = oldId; i < size; i++)
        {
            if (i - oldId < 5)
            {
                result.add(maps.get(i));
            }
        }

        map.put("success", true);
        map.put("message", result);

        return  map;

    }
}
