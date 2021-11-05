package com.vg.basic.juc.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockUseDemo {

//    private static Lock lock = new ReentrantLock();

    private Lock lock = new ReentrantLock();

//    private static ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();

    //单例，成功，先后获取锁
    private static ReenTrantLockUseDemo reenTrantLockUseDemo;
    public static ReenTrantLockUseDemo getInstance(){
        if (reenTrantLockUseDemo == null){
            reenTrantLockUseDemo = new ReenTrantLockUseDemo();
        }
        System.out.println(reenTrantLockUseDemo);
        return reenTrantLockUseDemo;
    }

    public void syncMethod() throws InterruptedException {
//        Lock lock = new ReentrantLock(); //失败，局部变量对象，多个线程并发获取锁

        lock.lock();
        System.out.println(Thread.currentThread().getName() + "：获取了锁");
        Thread.sleep(10000);
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + "：释放了锁");
    }

    public static void main(String[] args){
        //情况1：lock为静态变量时：只有一个线程拿到锁
        //情况2：lock为普通成员属性时：只有一个线程拿到锁
        ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();

        for(int i = 0; i < 5; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程新建一个对象
                    //情况1：lock为静态变量时：只有一个线程拿到锁
                    //情况2：lock为普通成员属性时：多个线程并发获得锁
//                    ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();

                    //获取同一个对象，单例情况下
                    //情况1：lock为静态变量时：只有一个线程拿到锁
                    //情况2：lock为普通成员属性时：只有一个线程拿到锁
//                    ReenTrantLockUseDemo reenTrantLockUseDemo = ReenTrantLockUseDemo.getInstance();
                    try {
                        reenTrantLockUseDemo.syncMethod();

                        //静态对象直接引用
                        //情况1：lock为静态变量时：只有一个线程拿到锁
                        //情况2：lock为普通成员属性时：只有一个线程拿到锁
//                        ReenTrantLockUseDemo.reenTrantLockUseDemo.syncMethod();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
