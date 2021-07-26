package com.vg.basic.lock.sync;

public class SyncLock {

    int i = 0;

    public void add(){
        synchronized (SyncLock.class){
            i++;
        }

    }

    public static void main(String[] args){
        int i  = 1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int age = i + 1;
            }
        }).start();

//        i = 12;
    }
}
