package com.vg.basic.design.create.AbstractFactory;

import com.vg.basic.design.create.AbstractFactory.factory.AbFactory;
import com.vg.basic.design.create.AbstractFactory.factory.AbFactoryA;
import com.vg.basic.design.create.AbstractFactory.factory.AbFactoryB;
import com.vg.basic.design.create.AbstractFactory.product.animal.AbAnimal;
import com.vg.basic.design.create.AbstractFactory.product.plant.AbPlant;

/**
 * @description:
 * @author: xieweij
 * @create: 2020-12-04 17:04
 **/
public class AbstractFactoryTest {

    public static void main(String[] args){
        AbFactory factory;
        AbAnimal animal;
        AbPlant plant;

        factory = new AbFactoryA();
        animal = factory.createAnimal();
        plant = factory.createPlant();

        animal.run();
        plant.growing();

        factory = new AbFactoryB();
        animal = factory.createAnimal();
        plant = factory.createPlant();

        animal.run();
        plant.growing();

    }
}
