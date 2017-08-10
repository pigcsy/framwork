package com.mapper;

import com.common.entity.UserBase;
import org.apache.ibatis.annotations.Param;

public interface UserBaseMapper extends tk.mybatis.mapper.common.Mapper<UserBase> {


    UserBase queryByUserName(@Param(value="userName")String userName);
}