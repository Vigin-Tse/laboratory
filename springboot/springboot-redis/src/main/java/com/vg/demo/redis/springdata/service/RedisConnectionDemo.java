package com.vg.demo.redis.springdata.service;

import com.vg.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisConnectionDemo {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 简单的缓存插入功能
     */
    public void setByCache(String userId, String userInfo) {
        stringRedisTemplate.opsForValue().set(userId, userInfo);
    }

    public User getUserById(Integer userId) {
        User user = null;

        // 1、 判定缓存中是否存在
        user = (User) this.redisTemplate.opsForValue().get(userId.toString());
        if (user != null) {
            System.out.println("从缓存中读取到值：" + user);
            return user;
        }

        //2、不存在则读取数据库或者其他地方的值
        user = new User(userId, "张三", 15);
        System.out.println("从数据库中读取到值：" + user);

        // 3、 同步存储value到缓存。
        this.redisTemplate.opsForValue().set(userId.toString(), user);
        return user;
    }


    /**
     * springcache注解版本（官方大部分资料开始往springboot方向引导，实际上不用springboot，也是差不多的方式）
     */
    // value~单独的缓存前缀
    // key缓存key 可以用springEL表达式
    @Cacheable(cacheManager = "cacheManager", value = "c-1", key = "#id")
    public User getBySpringCacheUseId(String id) {

        User user = new User(Integer.valueOf(id), "tony", 16);
        System.out.println("数据库：" + user);
        return user;
    }

    /**
     *
     */
    @CachePut(cacheManager = "cacheManager", value = "c-1", key = "#id", condition = "#result ne null ")
    public User updateUser(String id) {

        User user = new User(Integer.valueOf(id), "update", 16);
        System.out.println("数据库更新：" + user);
        return user;
    }
}
