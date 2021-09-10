package com.vg.aop.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * @author: xieweij
 * @time: 2021/9/2 9:31
 */
@Service
public class ServiceB implements BeanNameAware {

    private String name;

    public ServiceB(){
        System.out.println("Class ServiceB Init");
    }

    @Override
    public void setBeanName(String s) {
        this.name = s;
        System.out.println("bean's name : " + name);
    }
}
