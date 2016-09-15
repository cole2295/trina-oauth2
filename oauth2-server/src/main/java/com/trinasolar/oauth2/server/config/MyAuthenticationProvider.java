package com.trinasolar.oauth2.server.config;

import com.trinasolar.oauth2.server.user.domain.UserInfo;
import com.trinasolar.oauth2.server.user.service.UserInfoService;
import com.trinasolar.oauth2.server.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhm on 16-9-14.
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
    private UserInfoService userInfoService;
    public MyAuthenticationProvider(UserInfoService userInfoService){
        this.userInfoService = userInfoService;

    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        UserInfo user = userInfoService.findByUsername(username);

        // 2. Check the passwords match.
        if (!user.getPassword().equalsIgnoreCase(EncryptUtils.encodeMD5String(password))) {
            throw new BadCredentialsException("Bad Credentials");
        }

        // 3. Preferably clear the password in the user object before storing in authentication object
        user.clearPassword();

        // 4. Return an authenticated token, containing user data and authorities

        return new UsernamePasswordAuthenticationToken(user.getEmail(), null, getAuthorities(user));
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
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
