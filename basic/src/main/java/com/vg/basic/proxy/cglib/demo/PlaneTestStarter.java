package com.vg.basic.proxy.cglib.demo;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/8 15:23
 */
public class PlaneTestStarter {

    /**
     * Cblib总结:
     * 　　如果加入容器的目标对象有实现接口,用JDK代理
     * 　　如果目标对象没有实现接口,用Cglib代理 　　
     * 　　如果目标对象实现了接口，且强制使用cglib代理，则会使用cglib代理。
     */
    public static void main(String[] args){
        Plane targer = new Plane();
        Plane proxy = (Plane) new ProxyFactory(targer).getProxyInstance();
        proxy.fly();
    }
}
