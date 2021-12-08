package com.vg.basic.thread.pool;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: xieweij
 * @time: 2021/11/25 13:54
 */
public class ThreadPoolExecutorTest {

    public static void  main(String[] args){
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    try {
                        //  不干活，专门512kb的小对象
                        byte[] temp = new byte[1024 * 512];
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(new Random().nextInt(100)); // 随机睡眠200毫秒秒以内
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println(Thread.currentThread().getName());
            }).start();
        }, 100, 10000, TimeUnit.MILLISECONDS);
    }
    }

//    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue<Runnable> queues =  new ArrayBlockingQueue<>(1);
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 5000, TimeUnit.MILLISECONDS, queues);
//
//        for (int i = 4; i > 0; i--){
//            final int a = i;
//            System.out.println("前1：" + queues.size());
//            System.out.println("---总线程数：" + pool.getPoolSize() +"，活跃线程数：：" + pool.getActiveCount());
//            pool.execute(() -> {
//                try {
//                    System.out.println("线程【" + Thread.currentThread().getName() + "】获得任务" + a);
//                    if ("pool-1-thread-1".equals(Thread.currentThread().getName())){
//                        LockSupport.park();
//                    }else{
//                        Thread.sleep(1000L);
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            System.out.println("总线程数：" + pool.getPoolSize() +"，活跃线程数：：" + pool.getActiveCount());
////            System.out.println("前2：" + queues.size());
////            Thread.sleep(2000L);
////            System.out.println("后：" + queues.size());
//
//        }
//    }
//}
