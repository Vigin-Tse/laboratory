package com.vg.basic.design.structure.proxy.jdk;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/8 14:51
 */
public class CarTestStarter {

    /**
     * 动态代理总结：
     * 　　代理对象不需要实现接口,但是目标（委托）对象一定要实现接口,否则不能用动态代理。
     *     1.创建动态代理的接口（抽象类）和类
     *     2.创建实现了InvocationHandler接口的程序处理器类，对目标接口中的所有方法统一处理
     *     3.调用Proxy的静态方法newProxyInstance创建并生成相应的代理对象
     *     4.使用代理类
     * @param args
     */
    public static void main(String[] args){

        //目标（委托）对象
        Bus target = new Bus();

        //动态生成代理对象
        Car proxy = (Car) new CarProxyFactory(target).getProxyInstance();
        proxy.run();
    }
}
