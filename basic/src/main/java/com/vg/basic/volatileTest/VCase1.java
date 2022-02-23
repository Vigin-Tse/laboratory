package com.vg.basic.volatileTest;

/**
 * 测试 volatile 对 基本类型 的影响
 * @author: xieweij
 * @time: 2022/1/26 10:50
 */
public class VCase1{

    public boolean f = true;
//    public volatile boolean f = true;

    public static void main(String[] args) throws InterruptedException {

        VCase1 v = new VCase1();

        new Thread(() -> {
            while (v.f){
                //空转
            }
            System.out.println("t1退出了，v.f = " + v.f);
        }).start();

        Thread.sleep(3000L);
        v.f = false;
        System.out.println("通知t1退出，v.f = " + v.f);
    }
}
