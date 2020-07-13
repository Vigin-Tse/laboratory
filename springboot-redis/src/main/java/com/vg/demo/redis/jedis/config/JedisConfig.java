package com.vg.demo.redis.jedis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

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

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //制一个pool可分配多少个jedis实例
        jedisPoolConfig.setMaxTotal(1000);
        //控制一个pool最多有多少个状态为idle(空闲)的jedis实例
        jedisPoolConfig.setMaxIdle(8);
        //当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛JedisConnectionException；
        jedisPoolConfig.setMaxWaitMillis(100 * 1000);
        jedisPoolConfig.setMinIdle(0);
        return new JedisPool(jedisPoolConfig, host, port, 10000, ps);
    }

}
