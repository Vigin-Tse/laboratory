package com.vg.basic.juc.lock.reentrant.readWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试同一个线程 是否能同时获取读写锁
 */
public class RwLockDemo01 {

    public static void main(String[] args){
        RwLockDemo01Case c = new RwLockDemo01Case();
        c.case01(); //失败 测试同一个线程，先获取读锁，再获取写锁
        c.case02();// 成功 测试同一个线程，先获取写锁，再获取读锁---降级
    }


}

class RwLockDemo01Case{
    private final ReadWriteLock rw = new ReentrantReadWriteLock();

    //测试同一个线程，先获取读锁，再获取写锁
    public void case01(){
        rw.readLock().lock();
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + "，获取读锁成功");

            rw.writeLock().lock();
            try {
                System.out.println("线程：" + Thread.currentThread().getName() + "，获取写锁成功");
            } finally {
                rw.writeLock().unlock();
                System.out.println("线程：" + Thread.currentThread().getName() + "，释放写锁");
            }

        }finally {
            rw.readLock().unlock();
            System.out.println("线程：" + Thread.currentThread().getName() + "，释放读锁");
        }



    }

    //测试同一个线程，先获取写锁，再获取读锁
    public void case02() {
        rw.writeLock().lock();
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + "，获取写锁成功");

            rw.readLock().lock();
            try {
                System.out.println("线程：" + Thread.currentThread().getName() + "，获取读锁成功");
            } finally {
                rw.readLock().unlock();
                System.out.println("线程：" + Thread.currentThread().getName() + "，释放读锁");
            }

        } finally {
            rw.writeLock().unlock();
            System.out.println("线程：" + Thread.currentThread().getName() + "，释放写锁");
        }
    }
}