package com.service;

import com.alibaba.fastjson.JSONArray;
import com.common.constant.RedisKeyTemplate;
import com.core.redis.RedisCacheManager;
import com.service.impl.OauthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 预加载资源
 *
 * @author Administrator
 */
@Component
public class PreLoadMetadataSource {
    @Autowired
    RedisCacheManager redisCacheManager;

    @Autowired
    OauthServiceImpl oauthService;

    /**
     * TODO  初始加载数据
     */
    @PostConstruct
    public void loading() {
        String key = RedisKeyTemplate.AUTHORIZATION_METADATA_SOURCE.getRedisKey();
        // List<MetaDataSource> metaDataSourcesList = oauthService.queryAllResourcesByRole();
        JSONArray json = new JSONArray();
        // for (MetaDataSource metaDataSources : metaDataSourcesList) {
        //     JSONObject jo = new JSONObject();
        //     jo.put("role", metaDataSources.getRole());
        //     jo.put("systemType", metaDataSources.getSystemType());
        //     jo.put("urls", metaDataSources.getUrls());
        //     json.add(jo);
        // }
        redisCacheManager.put(key, json);
    }

}