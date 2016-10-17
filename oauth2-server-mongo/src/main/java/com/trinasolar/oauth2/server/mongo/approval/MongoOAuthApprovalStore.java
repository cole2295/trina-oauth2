package com.trinasolar.oauth2.server.mongo.approval;

import com.trinasolar.oauth2.server.mongo.approval.domain.MongoOAuthApproval;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus.APPROVED;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoOAuthApprovalStore implements ApprovalStore {
    private MongoOAuthApprovalRepository repository;

    private boolean handleRevocationsAsExpiry = false;

    public MongoOAuthApprovalStore(MongoOAuthApprovalRepository repository){
        this.repository = repository;
    }
    @Override
    public boolean addApprovals(Collection<Approval> approvals) {
        for(Approval approval:approvals){
            MongoOAuthApproval data = repository.findByUserIdAndClientIdAndScope(approval.getUserId(),approval.getClientId(),approval.getScope());
            if(data==null){
                data = new MongoOAuthApproval();
            }
            data.setUserId(approval.getUserId());
            data.setClientId(approval.getClientId());
            data.setScope(approval.getScope());
            data.setStatus((approval.getStatus() == null ? APPROVED : approval.getStatus()).toString());
            data.setExpiresAt(approval.getExpiresAt());
            data.setLastModifiedAt(approval.getLastUpdatedAt());
            repository.save(data);
        }
        return true;
    }

    @Override
    public boolean revokeApprovals(Collection<Approval> approvals) {
        boolean success = true;
        for (final Approval approval : approvals) {
            if (handleRevocationsAsExpiry) {
                MongoOAuthApproval dbData = repository.findByUserIdAndClientIdAndScope(approval.getUserId(),approval.getClientId(),approval.getScope());
                dbData.setExpiresAt(new Date());
                repository.save(dbData);
            }
            else {
                repository.deleteByUserIdAndClientIdAndScope(approval.getUserId(),approval.getClientId(),approval.getScope());
            }
        }
        return success;
    }

    @Override
    public Collection<Approval> getApprovals(String userId, String clientId) {
        Collection<MongoOAuthApproval> rs = repository.findByUserIdAndClientId(userId,clientId);
        Collection<Approval> results = new ArrayList<Approval>();
        for(MongoOAuthApproval a:rs){
            Approval.ApprovalStatus st = a.getStatus().equals("APPROVED")?Approval.ApprovalStatus.APPROVED:Approval.ApprovalStatus.DENIED;
            Approval tmp = new Approval(a.getUserId(), a.getClientId(), a.getScope(), a.getExpiresAt(), st, a.getLastModifiedAt());
            results.add(tmp);
        }
        return results;
    }
}
