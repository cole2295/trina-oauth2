package com.trinasolar.oauth2.server.config;

import com.trinasolar.oauth2.server.mongo.approval.MongoOAuthApprovalRepository;
import com.trinasolar.oauth2.server.mongo.approval.MongoOAuthApprovalStore;
import com.trinasolar.oauth2.server.mongo.authcode.MongoAuthorizationCodeServices;
import com.trinasolar.oauth2.server.mongo.authcode.MongoOAuthCodeRepository;
import com.trinasolar.oauth2.server.mongo.client.MongoOAuthClientDetailsRepository;
import com.trinasolar.oauth2.server.mongo.client.MongoOAuthClientDetailsService;
import com.trinasolar.oauth2.server.mongo.token.MongoOAuthAccessTokenRepository;
import com.trinasolar.oauth2.server.mongo.token.MongoOAuthRefreshTokenRepository;
import com.trinasolar.oauth2.server.mongo.token.MongoTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by zhm on 16-9-13.
 */
@Configuration
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MongoOAuthClientDetailsRepository clientDetailsRepository;
    @Autowired
    private MongoOAuthApprovalRepository mongoOAuthApprovalRepository;
    @Autowired
    private MongoOAuthCodeRepository mongoOAuthCodeRepository;
    @Autowired
    private MongoOAuthAccessTokenRepository mongoOAuthAccessTokenRepository;
    @Autowired
    private MongoOAuthRefreshTokenRepository mongoOAuthRefreshTokenRepository;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private ApprovalStore approvalStore;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }
    @Bean
    public ClientDetailsService clientDetailsService(){
        return new MongoOAuthClientDetailsService(clientDetailsRepository);
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authorizationCodeServices(authorizationCodeServices)
                .tokenStore(tokenStore)
                .approvalStore(approvalStore)
        ;
    }
    @Bean
    public TokenStore tokenStore() {
        return new MongoTokenStore(mongoOAuthAccessTokenRepository,mongoOAuthRefreshTokenRepository);
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new MongoOAuthApprovalStore(mongoOAuthApprovalRepository);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new MongoAuthorizationCodeServices(mongoOAuthCodeRepository);
    }
}
