package com.vg.basic.spring.beanlifecycle.application.context.aware;

import com.vg.basic.spring.beanlifecycle.application.context.aware.service.CommService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * 1.为什么使用AppplicationContextAware?
 *
 *       ApplicationContext是的BeanFactory 的子类，
 *       拥有更强大的功能，ApplicationContext可以在服务器启动的时候自动实例化所有的bean,
 *       而 BeanFactory只有在调用getBean()的时候才去实例化那个bean,
 *       这也是我们为什么要得到一个ApplicationContext对象，
 *       事实上Spring2相关的web应用默认使用的是ApplicationContext对象去实例化bean，
 *       换一句话说， 在服务器启动的时候，Spring容器就已经实例化好了一个ApplicationContext对象，所以我们要在老的代码里尝试去获取这个对象。
 *       但是如何才能得到一个ApplicationContext对象呢？
 *       方法很多，最常用的办法就是用
 *       ClassPathXmlApplicationContext， FileSystemClassPathXmlApplicationContext， FileSystemXmlApplicationContext 等对象去加载Spring配置文件，
 *       如：
 *       ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext-common.xml");
 *       AbcService abcService = (AbcService)appContext.getBean("abcService");
 *       这样做也是可以， 但是在加载Spring配置文件的时候，就会生成一个新的ApplicaitonContext对象而不是Spring容器帮我们生成的哪一个，
 *       这样就产生了冗余， 所以我们在这里不采用这种加载文件的方式，我们使用ApplicationContextAware让Spring容器传递自己生成的ApplicationContext给我们，
 *       然后我们把这个ApplicationContext设置成一个类的静态变量， 这样我们就随时都可以在老的代码里得到Application的对象了。
 *       (转载自   https://blog.csdn.net/kouwoo/article/details/43405109)
 *
 * 2. ApplicationContextAware接口作用 ?
 *
 *        加载Spring配置文件时，如果Spring配置文件中所定义或者注解自动注入的Bean类实现了ApplicationContextAware 接口，那么在加载Spring配置文件时，
 *        会自动调用ApplicationContextAware 接口中的方法： public void setApplicationContext (ApplicationContext context) throws BeansException
 * @Author xieweij
 * @create 2020/5/12 16:11
 */
@Component
public class ApplicationContextAwareSon implements ApplicationContextAware {

    public static ApplicationContext appContext;

    /***
     * 加载Spring配置文件时，如果Spring配置文件中所定义的Bean类实现了ApplicationContextAware 接口，
     * 那么在加载Spring配置文件时，会自动调用ApplicationContextAware 接口中的方法，获得ApplicationContext对象,
     * ApplicationContext对象是由spring注入的。前提必须在Spring配置文件中指定该类。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
        if (appContext != null){
            System.out.println("成功调用setApplicationContext()方法");
            CommService commService = (CommService)appContext.getBean("commService");
            commService.say();
        }
    }
}
