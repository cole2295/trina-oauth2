package com.trinasolar.oauth2.server.mongo.client;

import com.trinasolar.oauth2.server.mongo.client.domain.MongoOAuthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhm on 16-9-19.
 */
public interface MongoOAuthClientDetailsRepository extends MongoRepository<MongoOAuthClientDetails, String> {
    MongoOAuthClientDetails findByClientId(String clientId);
}
