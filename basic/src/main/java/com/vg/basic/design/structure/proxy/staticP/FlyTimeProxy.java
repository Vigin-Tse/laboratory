package com.vg.basic.design.structure.proxy.staticP;

/**
 * @Description 代理类，记录飞行时间
 * @Author xieweij
 * @create 2020/5/8 14:11
 */
public class FlyTimeProxy implements Flyable {

    private Flyable flyable;

    public FlyTimeProxy(Flyable flyable){
        this.flyable = flyable;
    }

    /**
     * 静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
     * 需要注意的是,代理对象与目标对象要实现相同的接口,然后通过调用相同的方法来调用目标对象的方法
     */
    @Override
    public void fly() {
        long start = System.currentTimeMillis();
        this.flyable.fly();
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
