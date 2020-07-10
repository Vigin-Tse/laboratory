package com.vg.demo.redis.jedis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/9 9:04
 */
@Configuration
public class JedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.ps}")
    private String ps;

    @Bean
    public Jedis Jedis(){
        Jedis jedis = new Jedis(host, port);
        jedis.auth(ps);
        return jedis;
    }

}
