package com.vg.basic.design.AbstractFactory.product.animal;

/**
 * @description: 具体产品-狗
 * @author: xieweij
 * @create: 2020-12-04 16:53
 **/
public class AbDog implements AbAnimal {

    @Override
    public void run() {
        System.out.println("狗在跑");
    }
}
