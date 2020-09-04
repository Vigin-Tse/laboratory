package com.vg.basic.proxy.staticproxy.demo;

/**
 * @Description 代理类，记录开始、结束日志
 * @Author xieweij
 * @create 2020/5/8 14:11
 */
public class FlylogProxy implements Flyable {

    private Flyable flyable;

    public FlylogProxy(Flyable flyable){
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("开始");
        this.flyable.fly();
        System.out.println("结束");
    }
}
