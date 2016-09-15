package com.trinasolar.oauth2.server.config;

import com.trinasolar.oauth2.server.user.domain.UserInfo;
import com.trinasolar.oauth2.server.user.service.UserInfoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by haiming.zhuang on 2016/9/13.
 */
public class CustomUserService implements UserDetailsService {
    private UserInfoService userInfoService;
    public CustomUserService(UserInfoService userInfoService){
        this.userInfoService = userInfoService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserInfo uinfo = userInfoService.findByUsername(username);
            if(uinfo==null){
                return null;
            }
            UserDetails result = new User(uinfo.getMobile(),uinfo.getPassword(),getAuthorities(uinfo));
            return result;
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(UserInfo user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        String[] limits = user.getUrls().split(",");
        for(String limit : limits) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(limit);
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
