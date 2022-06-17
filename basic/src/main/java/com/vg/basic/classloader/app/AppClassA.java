package com.vg.basic.classloader.app;

public class AppClassA {

    static {
        System.out.println("AppClassA 类初始化");
    }

    public void println(){
        System.out.println("我是AppClassA");
    }
}
