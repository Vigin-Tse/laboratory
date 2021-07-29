package com.vg.basic.lock.sync;

public class lockArea {

    public synchronized void test1() {
        int i =5;
        while (i-- >0) {
            System.out.println(Thread.currentThread().getName() +" : " + i);
            try {
                Thread.sleep(500);
            }catch (InterruptedException ie) {
            }
        }
    }

    public static synchronized void test2() {
        int i =5;
        while (i-- >0) {
            System.out.println(Thread.currentThread().getName() +" : " + i);
            try {
                Thread.sleep(500);
            }catch (InterruptedException ie) {
            }
        }
    }

    public static void main(String[] args) {
        final lockArea myt2 =new lockArea();

        Thread test2 =new Thread(new Runnable() {
            public void run() {
                lockArea.test2();
            }
        },"test2");

        Thread test1 =new Thread(new Runnable() {
            public void run() {
                myt2.test1();
            }
        },"test1");


        test1.start();
        test2.start();
    }

}
