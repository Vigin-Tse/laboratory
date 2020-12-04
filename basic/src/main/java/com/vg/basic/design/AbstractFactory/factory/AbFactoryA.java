package com.vg.basic.design.AbstractFactory.factory;

import com.vg.basic.design.AbstractFactory.product.animal.AbAnimal;
import com.vg.basic.design.AbstractFactory.product.animal.AbDog;
import com.vg.basic.design.AbstractFactory.product.plant.AbPlant;
import com.vg.basic.design.AbstractFactory.product.plant.AbTree;

/**
 * @description: 具体工厂A，生产狗和树
 * @author: xieweij
 * @create: 2020-12-04 16:58
 **/
public class AbFactoryA implements AbFactory{

    @Override
    public AbAnimal createAnimal() {
        System.out.println("生产狗");
        return new AbDog();
    }

    @Override
    public AbPlant createPlant() {
        System.out.println("生产树");
        return new AbTree();
    }
}
