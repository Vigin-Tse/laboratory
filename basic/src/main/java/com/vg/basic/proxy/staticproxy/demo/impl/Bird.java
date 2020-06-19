package com.vg.basic.proxy.staticproxy.demo.impl;

import com.vg.basic.proxy.staticproxy.demo.Flyable;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/8 14:10
 */
public class Bird implements Flyable {


    @Override
    public void fly() {
        System.out.println("bird is flying!");
    }
}
