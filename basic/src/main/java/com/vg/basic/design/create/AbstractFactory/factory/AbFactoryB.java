package com.vg.basic.design.create.AbstractFactory.factory;

import com.vg.basic.design.create.AbstractFactory.product.animal.AbAnimal;
import com.vg.basic.design.create.AbstractFactory.product.animal.AbCat;
import com.vg.basic.design.create.AbstractFactory.product.plant.AbFlower;
import com.vg.basic.design.create.AbstractFactory.product.plant.AbPlant;

/**
 * @description: 具体工厂B，生产猫和花
 * @author: xieweij
 * @create: 2020-12-04 17:01
 **/
public class AbFactoryB implements AbFactory {

    @Override
    public AbAnimal createAnimal() {
        System.out.println("生产猫");
        return new AbCat();
    }

    @Override
    public AbPlant createPlant() {
        System.out.println("生产花");
        return new AbFlower();
    }
}
