package com.vg.basic.thread.interrupt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: xieweij
 * @time: 2021/8/27 14:02
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {

        Thread th = new Thread(() -> {

            System.out.println("[TH]线程开始：" + Thread.currentThread().getState());
            //测试先设置中断标志位，再挂起线程的情况 ---th中断
            Thread.currentThread().interrupt();
            System.out.println("[TH]线程设置中断信号1：" + Thread.currentThread().isInterrupted());

            System.out.println("[TH]线程挂起");
//            try {
//                Thread.sleep(5000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            LockSupport.park();
            System.out.println("[TH]线程设置中断信号2：" + Thread.currentThread().isInterrupted());
            System.out.println("[TH]线程恢复执行：" + Thread.currentThread().getState());
        });

        th.start();
        Thread.sleep(2000L);
        System.out.println("[MAIN]线程th的状态：" + th.getState());

        //测试先挂起线程，再设置中断标志位再的情况 ---th中断
//        th.interrupt();
    }
}
