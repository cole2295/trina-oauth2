package com.trinasolar.oauth2.server.mongo.token;

import com.trinasolar.oauth2.server.mongo.token.domain.MongoOAuthAccessToken;
import com.trinasolar.oauth2.server.mongo.token.domain.MongoOAuthRefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoTokenStore implements TokenStore {

    private MongoOAuthAccessTokenRepository accessTokenRepository;
    private MongoOAuthRefreshTokenRepository refreshTokenRepository;
    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    public MongoTokenStore(MongoOAuthAccessTokenRepository accessTokenRepository,
                           MongoOAuthRefreshTokenRepository refreshTokenRepository){
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        OAuth2Authentication authentication = null;
        MongoOAuthAccessToken rs = accessTokenRepository.findByTokenId(token);
        return rs.getoAuth2Authentication();
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        MongoOAuthAccessToken oAuth2AuthenticationAccessToken = new MongoOAuthAccessToken(token,
                authentication, authenticationKeyGenerator.extractKey(authentication));
        accessTokenRepository.save(oAuth2AuthenticationAccessToken);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        MongoOAuthAccessToken token = accessTokenRepository.findByTokenId(tokenValue);
        if(token == null) {
            return null; //let spring security handle the invalid token
        }
        OAuth2AccessToken accessToken = token.getoAuth2AccessToken();
        return accessToken;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        MongoOAuthAccessToken accessToken = accessTokenRepository.findByTokenId(token.getValue());
        if(accessToken != null) {
            accessTokenRepository.delete(accessToken);
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        refreshTokenRepository.save(new MongoOAuthRefreshToken(refreshToken, authentication));
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return refreshTokenRepository.findByTokenId(tokenValue).getRefreshToken();
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return refreshTokenRepository.findByTokenId(token.getValue()).getoAuth2Authentication();
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        refreshTokenRepository.delete(refreshTokenRepository.findByTokenId(token.getValue()));
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshTokenRepository.findByRefreshToken(refreshToken.getValue()));
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        MongoOAuthAccessToken token =  accessTokenRepository.findByAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        return token == null ? null : token.getoAuth2AccessToken();
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<MongoOAuthAccessToken> tokens = accessTokenRepository.findByClientIdAndUserName(clientId, userName);
        return extractAccessTokens(tokens);
    }
    private Collection<OAuth2AccessToken> extractAccessTokens(List<MongoOAuthAccessToken> tokens) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();
        for(MongoOAuthAccessToken token : tokens) {
            accessTokens.add(token.getoAuth2AccessToken());
        }
        return accessTokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<MongoOAuthAccessToken> tokens = accessTokenRepository.findByClientId(clientId);
        return extractAccessTokens(tokens);
    }
}
