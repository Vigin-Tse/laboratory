package com.vg.aop.service;

import com.vg.aop.annotation.Mark;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * @author: xieweij
 * @time: 2021/9/2 9:31
 */
@Service
public class ServiceA implements BeanNameAware {

    private String name;

    public ServiceA(){
        System.out.println("Class ServiceA Init");
    }

    @Mark
    public void doMark(){
        System.out.println(name + " do mark execute");
    }

    public void doCommon(){
        System.out.println(name + " do common execute");
    }

    @Override
    public void setBeanName(String s) {
        this.name = s;
        System.out.println("bean's name : " + name);
    }
}
