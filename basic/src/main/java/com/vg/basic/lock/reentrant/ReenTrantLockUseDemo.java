package com.vg.basic.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockUseDemo {

    private static Lock lock = new ReentrantLock(); //成功，先后获取锁
//    private volatile Lock lock = new ReentrantLock(); //失败，并发获取锁

//    private static ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();

    //单例，成功，先后获取锁
    private static ReenTrantLockUseDemo reenTrantLockUseDemo;
    public static ReenTrantLockUseDemo getInstance(){
        if (reenTrantLockUseDemo == null){
            reenTrantLockUseDemo = new ReenTrantLockUseDemo();
        }
        return reenTrantLockUseDemo;
    }

    public void syncMethod() throws InterruptedException {
//        Lock lock = new ReentrantLock(); //失败，局部变量对象，并发获取锁

        lock.lock();
        System.out.println(Thread.currentThread().getName() + "：获取了锁");
        Thread.sleep(10000);
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + "：释放了锁");
    }

    public static void main(String[] args){
//        ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();
                ReenTrantLockUseDemo reenTrantLockUseDemo = ReenTrantLockUseDemo.getInstance();
                try {
                    reenTrantLockUseDemo.syncMethod();
//                    ReenTrantLockUseDemo.reenTrantLockUseDemo.syncMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                ReenTrantLockUseDemo reenTrantLockUseDemo = new ReenTrantLockUseDemo();
                ReenTrantLockUseDemo reenTrantLockUseDemo = ReenTrantLockUseDemo.getInstance();
                try {
                    reenTrantLockUseDemo.syncMethod();
//                    ReenTrantLockUseDemo.reenTrantLockUseDemo.syncMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
