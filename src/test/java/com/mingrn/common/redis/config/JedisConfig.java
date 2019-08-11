package com.mingrn.common.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JedisConfig {

    @Bean(initMethod = "initConnection", destroyMethod = "destroyConnection")
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }
}
