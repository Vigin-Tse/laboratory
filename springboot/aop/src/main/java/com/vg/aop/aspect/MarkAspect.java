package com.vg.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: xieweij
 * @time: 2021/9/2 13:24
 */
@Component
@Aspect
public class MarkAspect {

    @Pointcut("@annotation(com.vg.aop.annotation.Mark))")
    public void markPointCut(){}

    @Around("markPointCut()")
    public Object doAround(ProceedingJoinPoint jp){

        Object[] args = jp.getArgs();
        Object result = null;
        System.out.println("方法执行前增强");
        try {
            result = jp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("方法执行后增强");
        return result;
    }
}
