package com.trinasolar.oauth2.server.controller;

import com.trinasolar.oauth2.server.user.domain.UserInfo;
import com.trinasolar.oauth2.server.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhm on 16-9-13.
 */
@Controller
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping(value = "/me")
    public @ResponseBody
    Map<String,String> userInfo(Principal user){
        UserInfo userInfo = userInfoService.findByUsername(user.getName());
        Map<String,String> results = new HashMap<String, String>();
        results.put("username",userInfo.getUsername());
        results.put("email",userInfo.getEmail());
        results.put("mobile",userInfo.getMobile());
        results.put("urls",userInfo.getUrls());
        results.put("role",userInfo.getRole());
        return results;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "user_login";
    }
}
