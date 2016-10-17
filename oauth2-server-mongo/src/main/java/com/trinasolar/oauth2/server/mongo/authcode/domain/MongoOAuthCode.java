package com.trinasolar.oauth2.server.mongo.authcode.domain;

import org.springframework.data.annotation.Id;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoOAuthCode {
    @Id
    private String id;
    private String code;
    private OAuth2Authentication authentication;
    private int isuse;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OAuth2Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = authentication;
    }

    public MongoOAuthCode(String code, OAuth2Authentication authentication) {

        this.code = code;
        this.authentication = authentication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsuse() {
        return isuse;
    }

    public void setIsuse(int isuse) {
        this.isuse = isuse;
    }
}
