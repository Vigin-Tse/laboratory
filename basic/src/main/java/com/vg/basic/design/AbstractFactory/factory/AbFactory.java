package com.vg.basic.design.AbstractFactory.factory;

import com.vg.basic.design.AbstractFactory.product.animal.AbAnimal;
import com.vg.basic.design.AbstractFactory.product.plant.AbPlant;

/**
 * 抽象工厂
 */
public interface AbFactory {

    /**
     * 生产动物
     * @return
     */
    AbAnimal createAnimal();

    /**
     * 生产植物
     * @return
     */
    AbPlant createPlant();
}
