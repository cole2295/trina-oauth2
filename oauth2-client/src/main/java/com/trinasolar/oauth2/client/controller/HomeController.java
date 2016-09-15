package com.trinasolar.oauth2.client.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhm on 16-9-13.
 */
@RestController
public class HomeController {
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public Map<String,String> home(OAuth2Authentication auth){
        return (Map)auth.getUserAuthentication().getDetails();
    }
}
