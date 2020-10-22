package com.vg.basic.proxy.jdk.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 代理类
 * @Author xieweij
 * @create 2020/5/8 14:35
 */
public class CarProxyFactory {

    /**
     * 动态代理有以下特点:
     *
     * 1.代理对象,不需要实现接口
     * 2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
     * 3.动态代理也叫做:JDK代理,接口代理
     *
     * 代理类所在包:java.lang.reflect.Proxy
     * 　　JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数,完整的写法是
     * static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
     *
     * 注意该方法是在Proxy类中是静态方法,且接收的三个参数依次为:
     *
     * ClassLoader loader,:指定当前目标对象使用类加载器,获取加载器的方法是固定的
     * Class<?>[] interfaces,:目标对象实现的接口的类型,使用泛型方式确认类型
     * InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
     */


    /**
     * 目标（委托）对象
     */
    private Object target;

    public CarProxyFactory(Object target){
        this.target = target;
    }

    /**
     * 动态生成生成代理对象
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),/*类加载器*/
                target.getClass().getInterfaces(),/*让代理对象和目标对象实现相同接口*/
                new InvocationHandler() {/*代理对象的方法最终都会被JVM导向它的invoke方法*/
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("发动...");
                        //运用反射执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("停止...");
                        return returnValue;
                    }
                }
        );
    }
}
