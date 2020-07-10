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

    public int ticketCount = 10;

    public void buy(){
        new Thread(()->{ticktSub();}, "你").start();
        new Thread(()->{ticktSub();}, "我").start();
        new Thread(()->{ticktSub();}, "他").start();
    }

     private void ticktSub(){
         try {
             if (lock.lock()) {
                 System.out.println(Thread.currentThread() + "此时：" + ticketCount);
                 while (ticketCount > 0) {
                     System.out.println(Thread.currentThread() + "票号---" + ticketCount);
                     try {
                         Thread.sleep(500);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println(Thread.currentThread() + "买完之后的库存---" + --ticketCount);
                 }
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     }

}