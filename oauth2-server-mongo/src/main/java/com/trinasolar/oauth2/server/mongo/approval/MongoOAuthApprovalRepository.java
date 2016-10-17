package com.trinasolar.oauth2.server.mongo.approval;

import com.trinasolar.oauth2.server.mongo.approval.domain.MongoOAuthApproval;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

/**
 * Created by zhm on 16-10-14.
 */
public interface MongoOAuthApprovalRepository extends MongoRepository<MongoOAuthApproval,String> {

    Collection<MongoOAuthApproval> findByUserIdAndClientId(String userId, String clientId);

    void deleteByUserIdAndClientIdAndScope(String userId, String clientId, String scope);

    MongoOAuthApproval findByUserIdAndClientIdAndScope(String userId, String clientId, String scope);
}
