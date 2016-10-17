package com.trinasolar.oauth2.server.mongo.token.domain;

import org.springframework.data.annotation.Id;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoOAuthRefreshToken {
    @Id
    private String id;
    private String tokenId;
    private OAuth2RefreshToken refreshToken;
    private OAuth2Authentication oAuth2Authentication;

    public MongoOAuthRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication oAuth2Authentication) {
        this.refreshToken = refreshToken;
        this.oAuth2Authentication = oAuth2Authentication;
        this.tokenId = refreshToken.getValue();
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setRefreshToken(OAuth2RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public OAuth2RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public OAuth2Authentication getoAuth2Authentication() {
        return oAuth2Authentication;
    }

    public void setoAuth2Authentication(OAuth2Authentication oAuth2Authentication) {
        this.oAuth2Authentication = oAuth2Authentication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
