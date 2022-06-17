package com.vg.basic.spring.aop.invalid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author xieweij
 * @description: TODO
 * @date 2022/4/2114:58
 */
@Service
public class InvalidTestA {

    @Autowired
    private InvalidTestB invalidTestB;

    @PostConstruct
    public void init(){
        doCallA();
        doCallB();
    }

    public void doCallA(){
        System.out.println("======同类调用结果======");
        invalidTestB.method_A();
    }

    public void doCallB(){
        System.out.println("======跨类调用结果======");
        invalidTestB.method_B();
    }
}
