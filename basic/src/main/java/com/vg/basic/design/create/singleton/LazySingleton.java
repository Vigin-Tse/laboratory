package com.vg.basic.design.create.singleton;

/**
 * @description: 懒汉式单例
 * @author: xieweij
 * @create: 2020-11-05 11:46
 **/
public class LazySingleton {

    /**
     * volatile字段禁止多线程读取缓存，保存多线程环境下变量的可见性
     */
    private static volatile LazySingleton INSTANCE = null;

    /**
     * 构造函数访问权限设置private，表示该对象只能在内部创建，避免在类外部被实例化
     */
    private LazySingleton(){}

    /**
     * 外部获取实例只能通过这个方法
     * synchronized 保证在并发环境下只实例一次对象
     * @return
     */
    static synchronized LazySingleton getInstance(){

        //等第一次获取时才实例对象，逻辑上是可以去除的，不影响单例的正确性，但是去除之后效率低，不管instance是否已经初始化，都会进行synchronized操作，重操作消耗性能。
        // 加上之后，如果已经初始化直接返回结果，不会进行synchronized操作。
        if (INSTANCE == null){
            //双检锁，防止多线程环境下出现线程安全问题而多次创建对象
            synchronized (LazySingleton.class){
                if (INSTANCE == null){
                    INSTANCE = new LazySingleton();
                }
            }
        }

        return INSTANCE;
    }
}
