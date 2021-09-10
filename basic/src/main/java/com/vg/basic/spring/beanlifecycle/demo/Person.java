package com.vg.basic.spring.beanlifecycle.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * ApplicationContext Bean生命周期 演示
 * 1.首先容器启动后，会对scope为singleton且非懒加载的bean进行实例化，
 * 2.按照Bean定义信息配置信息，注入所有的属性，
 * 3.如果Bean实现了 BeanNameAware 接口，会回调该接口的setBeanName()方法，传入该Bean的id，此时该Bean就获得了自己在配置文件中的id，
 * 4.如果Bean实现了 BeanFactoryAware 接口,会回调该接口的setBeanFactory()方法，传入该Bean的BeanFactory，这样该Bean就获得了自己所在的BeanFactory，
 * 5.如果Bean实现了 ApplicationContextAware 接口,会回调该接口的setApplicationContext()方法，传入该Bean的ApplicationContext，这样该Bean就获得了自己所在的ApplicationContext，
 * 6.如果有Bean实现了 BeanPostProcessor 接口，则会回调该接口的postProcessBeforeInitialzation()方法，
 * 7.如果Bean实现了 InitializingBean 接口，则会回调该接口的afterPropertiesSet()方法，
 * 8.如果Bean配置了 init-method 方法，则会执行init-method配置的方法，
 * 9.如果有Bean实现了 BeanPostProcessor接口 ，则会回调该接口的postProcessAfterInitialization()方法，
 * 10.经过流程9之后，就可以正式使用该Bean了,对于scope为singleton的Bean,Spring的ioc容器中会缓存一份该bean的实例，而对于scope为prototype的Bean,每次被调用都会new一个新的对象，期生命周期就交给调用方管理了，不再是Spring容器进行管理了
 * 11.容器关闭后，如果Bean实现了DisposableBean接口，则会回调该接口的destroy()方法，
 * 12.如果Bean配置了destroy-method方法，则会执行destroy-method配置的方法，至此，整个Bean的生命周期结束
 *
 * result:
 * person bean生命周期：构造方法
 * person bean生命周期：调用BeanNameAware.setBeanName()，获得beanId = person
 * person bean生命周期：调用BeanFactoryAware.setBeanFactory()，获得自己所在的BeanFactory
 * person bean生命周期：调用ApplicationContextAware.setApplicationContext()，获得自己所在的applicationContext
 * person bean生命周期：调用BeanPostProcessor.postProcessBeforeInitialization()
 * person bean生命周期：调用InitializingBean.afterPropertiesSet()
 * person bean生命周期：调用BeanPostProcessor.postProcessAfterInitialization()
 * person bean生命周期：调用DisposableBean，销毁Bean。
 * @Author xieweij
 * @create 2020/5/13 9:10
 */
@Component
public class Person implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String beanName;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    public Person(){
        System.out.println("person bean生命周期：构造方法");
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
        System.out.println("person bean生命周期：调用BeanNameAware.setBeanName()，获得beanId = " + this.beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("person bean生命周期：调用BeanFactoryAware.setBeanFactory()，获得自己所在的BeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("person bean生命周期：调用ApplicationContextAware.setApplicationContext()，获得自己所在的applicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("person bean生命周期：调用InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("person bean生命周期：调用DisposableBean，销毁Bean。");
    }

    /**
     *测试 beanFactory 和 applicationContext 是否操作同一个容器
     * */
    public void isTheSameBean(){
        System.out.println("applicationContext.getObject=" + this.beanFactory.getBean("person"));
        System.out.println("applicationContext.getObject=" + this.applicationContext.getBean("person"));
    }
}


