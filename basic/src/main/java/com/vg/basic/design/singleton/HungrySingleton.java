package com.vg.basic.design.singleton;

/**
 * @description: 饿汉式单例
 * @author: xieweij
 * @create: 2020-11-05 13:33
 **/
public class HungrySingleton {

    /**
     * 该模式的特点是类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了。
     */
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton(){}

    /**
     * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
     * @return
     */
    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}
