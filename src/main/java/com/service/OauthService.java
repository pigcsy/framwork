package com.service;


import com.domain.oauth.AdminUserVo;
import com.domain.oauth.OauthSystemVo;

import java.util.List;

public interface OauthService {

    public OauthSystemVo queryDetailsByClientId(String clientId);

    public List<String> queryRoleByUserId(Integer userId);

    AdminUserVo queryByUserName(String userName);

    // public List<MetaDataSource> queryAllResourcesByRole();


}
