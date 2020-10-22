package com.vg.basic.proxy.cglib.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/8 15:16
 */
public class ProxyFactory implements MethodInterceptor {
    /**
     * 　上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象,
     * 但是有时候目标对象只是一个单独的对象,并没有实现任何的接口,这个时候就可以使用以目标对象子类的方式类实现代理,这种方法就叫做:Cglib代理
     *
     * Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
     *
     * JDK的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,如果想代理没有实现接口的类,就可以使用Cglib实现.
     * Cglib是一个强大的高性能的代码生成包,它可以在运行期扩展java类与实现java接口.它广泛的被许多AOP的框架使用,
     * 例如Spring AOP和synaop,为他们提供方法的interception(拦截)
     * Cglib包的底层是通过使用一个小而块的字节码处理框架ASM来转换字节码并生成新的类.
     * 不鼓励直接使用ASM,因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉.
     * Cglib子类代理实现方法:
     *
     * 1.需要引入cglib的jar文件,但是Spring的核心包中已经包括了Cglib功能,所以直接引入spring-core-3.2.5.jar即可.
     * 2.引入功能包后,就可以在内存中动态构建子类
     * 3.代理的类不能为final,否则报错
     * 4.目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法.
     * 5.如果方法为static,private则无法进行代理。
     * CGlib采用非常底层的字节码技术ASM，可以为一个类创建子类，并在子类中采用方法拦截技术拦截父类方法的调用，并顺势进行增强，即是织入横切逻辑
     */

    /**
     * 维护目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象创建一个代理对象
     * @return
     */
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("起飞...");

        //执行目标对象的方法
        Object returnValue = method.invoke(target, args);

        System.out.println("降落...");

        return returnValue;
    }
}
