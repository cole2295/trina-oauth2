package com.trinasolar.oauth2.server.mongo.token;

import com.trinasolar.oauth2.server.mongo.token.domain.MongoOAuthRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhm on 16-10-14.
 */
public interface MongoOAuthRefreshTokenRepository extends MongoRepository<MongoOAuthRefreshToken,String> {

    MongoOAuthRefreshToken findByTokenId(String tokenId);

    MongoOAuthRefreshToken findByRefreshToken(String refreshToken);
}
