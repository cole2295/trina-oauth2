package com.trinasolar.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by zhm on 16-9-13.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.trinasolar.oauth2.server","com.trinasolar.oauth2.user"})
public class Oauth2ServerApplication{
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class,args);
    }
}
