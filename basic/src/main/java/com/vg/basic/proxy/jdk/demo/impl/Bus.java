package com.vg.basic.proxy.jdk.demo.impl;

import com.vg.basic.proxy.jdk.demo.Car;

/**
 * @Description 目标类
 * @Author xieweij
 * @create 2020/5/8 14:34
 */
public class Bus implements Car {
    @Override
    public void run() {
        System.out.println("Bus is running");
    }
}
