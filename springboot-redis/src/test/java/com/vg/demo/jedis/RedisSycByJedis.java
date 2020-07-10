package com.vg.demo.jedis;

import com.vg.demo.redis.jedis.lock.RedisLock;
import com.vg.demo.redis.jedis.service.RedisLockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/9 10:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSycByJedis {

    @Autowired
    private RedisLockService redisLockService;

    @Autowired
    private Jedis jedis;

    @Autowired
    private RedisLock lock;

    @Test
    public void buyTicket() throws InterruptedException {
        redisLockService.buy();

        Thread.sleep(5000);

        System.out.println("票存:" + redisLockService.ticketCount);
    }

    @Test
    public void jedisConn(){

        for(int i = 10; i > 0; i--){
            new Thread(()->{
                jedis.set(UUID.randomUUID().toString(), "value", "NX", "EX", 30);
            }).start();
        }

    }

    @Test
    public void jedisLock(){
        try {
            System.out.println(lock.lock());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
