package com.trinasolar.oauth2.server.mongo.token;

import com.trinasolar.oauth2.server.mongo.token.domain.MongoOAuthAccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by zhm on 16-10-14.
 */
public interface MongoOAuthAccessTokenRepository extends MongoRepository<MongoOAuthAccessToken, String> {

    MongoOAuthAccessToken findByTokenId(String tokenId);

    MongoOAuthAccessToken findByAuthenticationId(String authenticationId);

    List<MongoOAuthAccessToken> findByClientId(String clientId);

    List<MongoOAuthAccessToken> findByClientIdAndUserName(String clientId, String userName);
}
