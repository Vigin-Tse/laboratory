package com.vg.demo.redis.jedis.service;

import com.vg.demo.redis.jedis.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/9 9:53
 */
@Service
public class RedisLockService {

    @Autowired
    private RedisLock lock;

    public volatile int ticketCount = 10;

    public void buy() {
        new Thread(() -> {
            ticktSub();
        }, "你").start();
        new Thread(() -> {
            ticktSub();
        }, "我").start();
        new Thread(() -> {
            ticktSub();
        }, "他").start();
    }

    private void ticktSub() {
        while (ticketCount > 0) {
            try {
                if (lock.lock()) {

                    //TODO 如果业务逻辑执行时间过长，要考虑redis锁时间失效
                    //...

                    //此if判断防止redis锁时间超时，导致其他线程获取了锁，库存已经<=0
                    if (ticketCount > 0) {
                        System.out.println(Thread.currentThread() + "：抢得票号：" + ticketCount + "，余票---" + --ticketCount);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}