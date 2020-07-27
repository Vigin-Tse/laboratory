package com.vg.demo;

import com.vg.demo.model.User;
import com.vg.demo.redis.springdata.service.RedisConnectionDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest01 {

    @Autowired
    RedisConnectionDemo redisConn;

    @Test
    public void getUserCache(){
        User user = this.redisConn.getUserById(1);
    }

    @Test
    public void setStringRedisTemplate(){
        this.redisConn.setByCache("2","stringRedisTemplate");
    }

    @Test
    public void getBySpringCacheUseId(){
        User user = this.redisConn.getBySpringCacheUseId("1");
        System.out.println(user);
    }

    @Test
    public void updateUser(){
        User user = this.redisConn.updateUser("1");
        System.out.println(user);
    }

}
