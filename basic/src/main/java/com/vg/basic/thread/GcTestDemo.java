package com.vg.basic.thread;

/**
 * @author: xieweij
 * @time: 2021/12/16 16:20
 */
public class GcTestDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("system start");
        while(true){
            byte[] b = new byte[1024 * 1024 * 10];
            Thread.sleep(3000L);
        }
    }
}
