package com.trinasolar.oauth2.server.mongo.client;

import com.trinasolar.oauth2.server.mongo.client.domain.MongoOAuthClientDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by zhm on 16-10-14.
 */
public class MongoOAuthClientDetailsService implements ClientDetailsService{
    private static final Log logger = LogFactory.getLog(JdbcClientDetailsService.class);
    private MongoOAuthClientDetailsRepository clientDetailsRepository;
    public MongoOAuthClientDetailsService(MongoOAuthClientDetailsRepository clientDetailsRepository){
        this.clientDetailsRepository = clientDetailsRepository;
    }
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        JsonMapper mapper = createJsonMapper();
        MongoOAuthClientDetails rs = clientDetailsRepository.findByClientId(clientId);
        BaseClientDetails details = new BaseClientDetails(rs.getClientId(), rs.getResourceIds(), rs.getScope(),
                rs.getAuthorizedGrantTypes(), rs.getAuthorities(), rs.getWebServerRedirectUri());
        details.setClientSecret(rs.getClientSecret());
        details.setAccessTokenValiditySeconds(rs.getAccessTokenValidity());
        details.setRefreshTokenValiditySeconds(rs.getRefreshTokenValidity());
        details.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(rs.getAutoapprove()));
        String json = rs.getAdditionalInformation();
        if (json != null) {
            try {
                @SuppressWarnings("unchecked")
                Map<String, Object> additionalInformation = mapper.read(json, Map.class);
                details.setAdditionalInformation(additionalInformation);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return details;
    }
    interface JsonMapper {
        String write(Object input) throws Exception;

        <T> T read(String input, Class<T> type) throws Exception;
    }

    private static JsonMapper createJsonMapper() {
        if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", null)) {
            return new JacksonMapper();
        }
        else if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", null)) {
            return new Jackson2Mapper();
        }
        return new NotSupportedJsonMapper();
    }

    private static class JacksonMapper implements JsonMapper {
        private org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

        @Override
        public String write(Object input) throws Exception {
            return mapper.writeValueAsString(input);
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class Jackson2Mapper implements JsonMapper {
        private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        @Override
        public String write(Object input) throws Exception {
            return mapper.writeValueAsString(input);
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class NotSupportedJsonMapper implements JsonMapper {
        @Override
        public String write(Object input) throws Exception {
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }
    }
}
