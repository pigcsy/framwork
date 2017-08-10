package com.mapper;

import com.common.entity.OauthSystem;
import org.apache.ibatis.annotations.Param;

public interface OauthSystemMapper extends tk.mybatis.mapper.common.Mapper<OauthSystem> {


    OauthSystem queryDetailsByClientId(@Param(value="clientId") String clientId);
}