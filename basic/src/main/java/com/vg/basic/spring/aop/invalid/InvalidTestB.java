package com.vg.basic.spring.aop.invalid;

import org.springframework.stereotype.Service;

/**
 * @author xieweij
 * @description: TODO
 * @date 2022/4/2114:58
 */
@Service
public class InvalidTestB {

    public void method_A(){
        method_B();
    }

    public void method_B(){
        System.out.println("方法B执行");
    }
}
