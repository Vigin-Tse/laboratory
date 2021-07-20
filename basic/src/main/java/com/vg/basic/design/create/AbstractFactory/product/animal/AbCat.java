package com.vg.basic.design.create.AbstractFactory.product.animal;

/**
 * @description: 具体产品-猫
 * @author: xieweij
 * @create: 2020-12-04 16:54
 **/
public class AbCat implements AbAnimal {
    @Override
    public void run() {
        System.out.println("猫在跑");
    }
}
