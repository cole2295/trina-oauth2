package com.trinasolar.oauth2.server.mongo.token.domain;

import org.springframework.data.annotation.Id;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoOAuthAccessToken {
        @Id
        private String id;
        private String tokenId;
        private OAuth2AccessToken oAuth2AccessToken;
        private String authenticationId;
        private String userName;
        private String clientId;
        private OAuth2Authentication oAuth2Authentication;
        private String refreshToken;
        public MongoOAuthAccessToken() {
        }
        public MongoOAuthAccessToken(final OAuth2AccessToken oAuth2AccessToken, final OAuth2Authentication oAuth2Authentication, final String authenticationId) {
            this.tokenId = oAuth2AccessToken.getValue();
            this.oAuth2AccessToken = oAuth2AccessToken;
            this.authenticationId = authenticationId;
            this.userName = oAuth2Authentication.getName();
            this.clientId = oAuth2Authentication.getOAuth2Request().getClientId();
            this.oAuth2Authentication = oAuth2Authentication;
            this.refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
        }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public OAuth2AccessToken getoAuth2AccessToken() {
        return oAuth2AccessToken;
    }

    public void setoAuth2AccessToken(OAuth2AccessToken oAuth2AccessToken) {
        this.oAuth2AccessToken = oAuth2AccessToken;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public OAuth2Authentication getoAuth2Authentication() {
        return oAuth2Authentication;
    }

    public void setoAuth2Authentication(OAuth2Authentication oAuth2Authentication) {
        this.oAuth2Authentication = oAuth2Authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
