package com.mapper;

import com.common.entity.OauthSystem;
import com.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface OauthSystemMapper extends MyMapper<OauthSystem> {


    OauthSystem queryDetailsByClientId(@Param(value = "clientId") String clientId);
}