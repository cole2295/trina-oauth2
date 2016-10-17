package com.trinasolar.oauth2.server.config;

import com.mongodb.*;
import com.trinasolar.oauth2.server.mongo.token.OAuth2AuthenticationReadConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写spring给mongodb的默认配置
 * Created by zhm on 16-9-19.
 */
@Configuration
@EnableMongoRepositories("com.trinasolar.oauth2.server.mongo")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.authentication-database}")
    private String authDatabase;

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    public Mongo mongo() throws Exception {
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(MongoCredential.createCredential(this.username,
                    authDatabase, this.password.toCharArray()));
        return new MongoClient(
                new ServerAddress(mongoHost, mongoPort),
                credentials, MongoClientOptions.builder()
                    .socketTimeout(10000)
                    .serverSelectionTimeout(10000)
                    .minHeartbeatFrequency(25)
                    .heartbeatSocketTimeout(10000)
                    .build());

    }
    @Bean
    public CustomConversions customConversions(){
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        OAuth2AuthenticationReadConverter converter = new OAuth2AuthenticationReadConverter();
        converterList.add(converter);
        return new CustomConversions(converterList);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
        return mongoTemplate;
    }
}
