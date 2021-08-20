package com.vg.basic.lock.sync;

public class SyncLock {

    int i = 0;

    public void add(){
        synchronized (SyncLock.class){
            i++;
        }

    }

    public static void main(String[] args){

        Number number = 11;
        Integer integer = 12;

        number = integer;


        System.out.println(number);
    }
}
