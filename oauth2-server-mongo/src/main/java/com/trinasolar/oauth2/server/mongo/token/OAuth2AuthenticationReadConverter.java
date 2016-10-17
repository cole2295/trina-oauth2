package com.trinasolar.oauth2.server.mongo.token;

import com.mongodb.DBObject;
import com.trinasolar.oauth2.server.user.domain.UserInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.*;

/**
 * Created by zhm on 16-10-16.
 */
@ReadingConverter
@SuppressWarnings("rawtypes")
public class OAuth2AuthenticationReadConverter implements Converter<DBObject, OAuth2Authentication> {

    @Override
    @SuppressWarnings("unchecked")
    public OAuth2Authentication convert(DBObject source) {
        DBObject storedRequest = (DBObject)source.get("storedRequest");
        HashSet resourceIds = null;
        if(storedRequest.get("resourceIds")!=null){
            resourceIds = new HashSet((List)storedRequest.get("resourceIds"));
        }
        HashSet responseTypes = null;
        if(storedRequest.get("responseTypes")!=null){
            responseTypes = new HashSet((List)storedRequest.get("responseTypes"));
        }
        HashSet authorities = null;
        if(storedRequest.get("authorities")!=null){
            authorities = new HashSet((List)storedRequest.get("authorities"));
        }
        HashMap extensions = null;
        if(storedRequest.get("extensions")!=null){
            extensions = (HashMap)storedRequest.get("extensions");
        }
        OAuth2Request oAuth2Request = new OAuth2Request((Map<String, String>)storedRequest.get("requestParameters"),
                (String)storedRequest.get("clientId"), authorities, true, new HashSet((List)storedRequest.get("scope")),
                resourceIds, storedRequest.get("redirectUri")==null?null:((String)storedRequest.get("redirectUri")), responseTypes, extensions);
        DBObject userAuthorization = (DBObject)source.get("userAuthentication");
        Object principal = getPrincipalObject(userAuthorization.get("principal"));

        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(principal,
                (String)userAuthorization.get("credentials"), getAuthorities((List) userAuthorization.get("authorities")));

        OAuth2Authentication authentication = new OAuth2Authentication(oAuth2Request,
                userAuthentication );
        return authentication;
    }

    private Object getPrincipalObject(Object principal) {
        if(principal instanceof DBObject) {
            return null;
        } else {
            return principal;
        }
    }

    private Collection<GrantedAuthority> getAuthorities(List<Map<String, String>> authorities) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>(authorities.size());
        for(Map<String, String> authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.get("role")));
        }
        return grantedAuthorities;
    }

}
