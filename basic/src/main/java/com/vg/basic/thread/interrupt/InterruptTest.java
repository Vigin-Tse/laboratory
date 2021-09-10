package com.vg.basic.thread.interrupt;

/**
 * @author: xieweij
 * @time: 2021/8/27 14:02
 */
public class InterruptTest {

    public static void main(String[] args){

        Thread th = new Thread(() -> {

            System.out.println("线程开始：" + Thread.currentThread().getState());

            try {
                System.out.println("线程 watting");
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                System.out.println("线程 结束 watting "+ Thread.currentThread().getState());
                e.printStackTrace();
            }
            System.out.println("线程恢复执行：" + Thread.currentThread().getState());
        });

        th.start();

    }
}
