package com.vg.basic.spring.beanlifecycle.bean.post.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Description
 * 当一个BeanPostProcessor的实现类注册到Spring IOC容器后，对于该Spring IOC容器所创建的每个bean实例在初始化方法
 * （如afterPropertiesSet和任意已声明的init方法）调用前，
 * 将会调用BeanPostProcessor中的postProcessBeforeInitialization方法，而在bean实例初始化方法调用完成后，
 * 则会调用BeanPostProcessor中的postProcessAfterInitialization方法，整个调用顺序可以简单示意如下：
 *
 * --> Spring IOC容器实例化Bean
 * --> 调用BeanPostProcessor的postProcessBeforeInitialization方法
 * --> 调用bean实例的初始化方法
 * --> 调用BeanPostProcessor的postProcessAfterInitialization方法
 *
 * 可以看到，Spring容器通过BeanPostProcessor给了我们一个机会对Spring管理的bean进行再加工。比如：我们可以修改bean的属性，
 * 可以给bean生成一个动态代理实例等等。一些Spring AOP的底层处理也是通过实现BeanPostProcessor来执行代理包装逻辑的。
 *
 * @Author xieweij
 * @create 2020/5/12 16:41
 */
@Component
public class BeanPostProcessorSon implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean:" + beanName + "，初始化前");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean:" + beanName + "，初始化后");
        return bean;
    }
}
