package com.service;


import com.domain.oauth.AdminUserVo;
import com.domain.oauth.OauthSystemVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OauthService {

    public OauthSystemVo queryDetailsByClientId(String clientId);

    public List<String> queryRoleByUserId(Integer userId);

    AdminUserVo queryByUserName(String userName);

    // public List<MetaDataSource> queryAllResourcesByRole();


}
