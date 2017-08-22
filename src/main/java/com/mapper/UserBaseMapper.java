package com.mapper;

import com.common.entity.UserBase;
import com.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface UserBaseMapper extends MyMapper<UserBase> {


    UserBase queryByUserName(@Param(value = "userName") String userName);
}