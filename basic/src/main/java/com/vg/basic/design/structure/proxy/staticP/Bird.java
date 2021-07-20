package com.vg.basic.design.structure.proxy.staticP;

import java.util.Random;

/**
 * @Description  公共类
 * @Author xieweij
 * @create 2020/5/8 14:10
 */
public class Bird implements Flyable {


    @Override
    public void fly() {
        System.out.println("bird is flying!");

        //线程睡眠，模拟飞行时间
        try {
            Thread.sleep(new Random().nextInt(1000));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
