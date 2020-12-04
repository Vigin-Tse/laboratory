package com.vg.basic.design.AbstractFactory.product.plant;

/**
 * @description: 具体产品-树
 * @author: xieweij
 * @create: 2020-12-04 16:55
 **/
public class AbTree implements AbPlant {
    @Override
    public void growing() {
        System.out.println("树长叶子");
    }
}
