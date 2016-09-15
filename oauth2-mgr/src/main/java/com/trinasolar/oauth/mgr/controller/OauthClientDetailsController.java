package com.trinasolar.oauth.mgr.controller;

import com.trinasolar.oauth.mgr.domain.OauthClientDetails;
import com.trinasolar.oauth.mgr.domain.Page;
import com.trinasolar.oauth.mgr.service.OauthClientDetailsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 * Created by zhm on 16-9-14.
 */
@Controller
public class OauthClientDetailsController {
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String root(){
        return "redirect:/oauthClient";
    }
    @RequestMapping(value = "/oauthClient",method = RequestMethod.GET)
    public String listOauthClient(@RequestParam(value = "clientid",defaultValue = "") String clientid,ModelMap model,
                                  @RequestParam(value = "page",defaultValue = "1") int page,
                                  @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        int start = (page-1)*pagesize;
        List<OauthClientDetails> results = oauthClientDetailsService.findByCond("%"+clientid+"%",start,pagesize);
        model.addAttribute("results",results);
        Long total = oauthClientDetailsService.findTotalByCond("%"+clientid+"%");
        Page pg = new Page(page,pagesize,total.intValue());
        model.addAttribute("page",pg);
        model.addAttribute("clientid",clientid);
        return "clients/listOauthClient";
    }
    @RequestMapping(value = "/oauthClient",method = RequestMethod.POST)
    public @ResponseBody String addClient(String client_name,String redirect_uri){
        OauthClientDetails data = new OauthClientDetails();
        data.setAdditional_information("{}");
        data.setAuthorities("");
        data.setAuthorized_grant_types("password,authorization_code,refresh_token");
        data.setAutoapprove("read");
        data.setClient_id(String.valueOf(newClientid()));
        data.setClient_secret(new ObjectId().toString());
        data.setClient_name(client_name);
        data.setResource_ids("trina_user");
        data.setScope("read,write,trust");
        data.setWeb_server_redirect_uri(redirect_uri);
        oauthClientDetailsService.saveData(data);
        return "SUCCESS";
    }
    private long newClientid() {
        Random random = new Random();
        long clientid;
        while (true) {
            clientid = (long) (1e9 + random.nextDouble() * 9e9);
            if (oauthClientDetailsService.findTotalByCond("%"+clientid+"%") == 0) {
                break;
            }
        }
        return clientid;
    }
}
