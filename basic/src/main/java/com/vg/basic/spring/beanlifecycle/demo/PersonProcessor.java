package com.vg.basic.spring.beanlifecycle.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/13 9:41
 */

@Component
public class PersonProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("person bean生命周期：调用BeanPostProcessor.postProcessBeforeInitialization()");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)) {
            System.out.println("person bean生命周期：调用BeanPostProcessor.postProcessAfterInitialization()");

            //1.测试srping 自动创建的对象和容器内（beanfactory、applicationContext里的是否同一个）
            //2.测试beanfactory 和 applicationContext 是否操作同一个容器
            System.out.println("person=" + bean);
            Person person = (Person) bean;
            person.isTheSameBean();
        }
        return bean;
    }
}
