package com.nqminhuit.gateway.messages.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {

    @Bean
    public Jedis setup() {
        return new Jedis();
    }
}
