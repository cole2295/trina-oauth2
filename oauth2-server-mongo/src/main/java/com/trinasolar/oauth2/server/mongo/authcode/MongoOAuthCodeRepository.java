package com.trinasolar.oauth2.server.mongo.authcode;

import com.trinasolar.oauth2.server.mongo.authcode.domain.MongoOAuthCode;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhm on 16-10-14.
 */
public interface MongoOAuthCodeRepository extends MongoRepository<MongoOAuthCode,String> {
    MongoOAuthCode findByCode(String code);
}
