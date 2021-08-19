package com.vg.basic.lock.sync;

public class SyncLock {

    int i = 0;

    public void add(){
        synchronized (SyncLock.class){
            i++;
        }

    }

    public static void main(String[] args){
        System.out.println(10 % 2);
        System.out.println(10 & (2 - 1));

        System.out.println("------------------");
        System.out.println(10 % 3);
        System.out.println(10 & (3 - 1));

        System.out.println("------------------");
        System.out.println(10 % 4);
        System.out.println(10 & (4 - 1));

        System.out.println("------------------");
        System.out.println(10 % 7);
        System.out.println(10 & (7 - 1));

        System.out.println("------------------");
        System.out.println(10 % 8);
        System.out.println(10 & (8 - 1));
    }
}
