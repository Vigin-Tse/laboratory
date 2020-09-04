package com.vg.basic.proxy.staticproxy.demo;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/8 14:15
 */
public class FlyTestStarter {

    /**
     *  静态代理总结:
     * 1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
     * 2.缺点:因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
     * @param args
     */
    public static void main(String[] args){

        //委托类
        Bird bird = new Bird();

        //代理类
//        FlylogProxy p1 = new FlylogProxy(bird);
//        FlyTimeProxy p2 = new FlyTimeProxy(p1);
//
//        p2.fly();

        //可调换顺序
        FlyTimeProxy p1 = new FlyTimeProxy(bird);
        FlylogProxy p2 = new FlylogProxy(p1);

        p2.fly();
    }
}
