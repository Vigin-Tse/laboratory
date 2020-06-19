package com.vg.basic.spring.beanlifecycle.application.context.aware.service;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/12 16:21
 */
@Service
public class CommService {

    public void say(){
        System.out.println("hey!");
    }
}
