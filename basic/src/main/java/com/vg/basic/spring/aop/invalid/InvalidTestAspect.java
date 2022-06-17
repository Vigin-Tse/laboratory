package com.vg.basic.spring.aop.invalid;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xieweij
 * @description: TODO
 * @date 2022/4/2115:55
 */
@Aspect
@Component
public class InvalidTestAspect {

    @Pointcut("execution(* com.vg.basic.spring.aop.invalid.InvalidTestB.method_B())")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeCall(){
        System.out.println("[代理]:执行前调用");
    }
}
