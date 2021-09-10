package com.vg.aop;

import com.vg.aop.service.ServiceA;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

//@SpringBootApplication
@ComponentScan("com.vg")
@EnableAspectJAutoProxy//proxyTargetClass：true——使用CGLIB基于类创建代理；false——（默认）使用java接口创建代理; //exposeProxy: 是否通过aop框架暴露该代理对象，aopContext能够访问
public class AopApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext atc = new AnnotationConfigApplicationContext();
        atc.register(AopApplication.class);
        atc.refresh();

        ServiceA serviceA = (ServiceA) atc.getBean("serviceA");
        serviceA.doCommon();
        serviceA.doMark();

        atc.close();

//        SpringApplication.run(AopApplication.class, args);
    }

}
