package com.trinasolar.oauth2.server.mongo.authcode;

import com.trinasolar.oauth2.server.mongo.authcode.domain.MongoOAuthCode;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {
    private MongoOAuthCodeRepository repository;
    public MongoAuthorizationCodeServices(MongoOAuthCodeRepository repository){
        this.repository = repository;
    }
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        MongoOAuthCode data = new MongoOAuthCode(code,authentication);
        repository.save(data);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        MongoOAuthCode data = repository.findByCode(code);
        data.setIsuse(1);
        repository.save(data);
        return data.getAuthentication();
    }

}
