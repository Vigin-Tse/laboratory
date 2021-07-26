package com.vg.basic.lock.reentrant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViginQueueDemo {

//    public static void main(String args[]) throws InterruptedException {
//        ViginQueue bb = new ViginQueue(5);
//
//        new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 20; i++) {
//                    String x = "x" + i;
//                    System.out.println(x);
//                    bb.put(x);
//                }
//            }
//        }.start();
//
//        Thread.sleep(3000L);
//        System.out.println("开始从队列中取元素...");
//        for (int i = 0; i < 10; i++) {
//            bb.take();
//            Thread.sleep(3000L);
//        }
//    }

    public static void main(String args[]) throws InterruptedException {
        ViginQueue bb = new ViginQueue(5);

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    bb.put("x" + i);
                }
                System.out.println("元素添加完成");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("没有死锁");
                bb.put("x" + 3);
            }
        }.start();



        Thread.sleep(2000L);
        System.out.println("开始从队列中取元素...");

        for (int i = 0; i < 3; i++) {
            bb.take();
//            Thread.sleep(3000L);
        }
    }
}

/*
  1、自己实现一个阻塞队列，只能存储  n个元素
   put时，若队列未满，直接put，
         若队列满，就阻塞，直到再有空间
   get时，若队列中有元素，则获取到元素
         若无元素，则等待元素
 */


class ViginQueue{
    List<Object> list = new ArrayList<>();

    Lock lock = new ReentrantLock();
    Condition putCondition = lock.newCondition();
    Condition takeCondition = lock.newCondition();

    private int length;

    public ViginQueue(int length){
        this.length = length;
    }

    public void put(Object obj){
        lock.lock();
        try {
            for(;;){
                if (list.size() < length){
                    list.add(obj);
                    System.out.println("put:" + obj);
                    takeCondition.signal();
                    System.out.println("put:" + obj + ":takeCondition.signal()");
                    break;
                }else{
                    putCondition.await();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Object take(){
        lock.lock();
        Object obj = null;
        try {
            for (;;) {
                if (list.size() > 0) {
                    obj = list.get(0);
                    list.remove(0);
                    System.out.println("take：" + obj);

                    putCondition.signal();
                    break;
                } else {
                    takeCondition.await();
                    System.out.println("takeCondition.await()");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return obj;
    }
}
