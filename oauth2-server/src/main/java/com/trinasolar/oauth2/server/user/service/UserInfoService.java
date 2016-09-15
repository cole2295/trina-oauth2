package com.trinasolar.oauth2.server.user.service;

import com.trinasolar.oauth2.server.user.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * Created by zhm on 16-9-13.
 */
@Mapper
@Service("userInfoService")
public interface UserInfoService {
    @Select("select * from user_info where email=#{username} or mobile=#{username}")
    UserInfo findByUsername(@Param("username") String username);
}
