package com.vg.basic.design.create.AbstractFactory.product.plant;

/**
 * @description: 具体产品-花
 * @author: xieweij
 * @create: 2020-12-04 16:56
 **/
public class AbFlower implements AbPlant {
    @Override
    public void growing() {
        System.out.println("花在绽放");
    }
}
