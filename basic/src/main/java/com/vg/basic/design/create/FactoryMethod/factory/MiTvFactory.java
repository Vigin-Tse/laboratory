package com.vg.basic.design.create.FactoryMethod.factory;

import com.vg.basic.design.create.FactoryMethod.tv.MiTv;
import com.vg.basic.design.create.FactoryMethod.tv.Tv;

/**
 * @description: 具体工厂，小米电视工厂
 * @author: xieweij
 * @create: 2020-11-12 16:48
 **/
public class MiTvFactory implements TvFactory {

    @Override
    public Tv createTv() {
        return new MiTv();
    }
}
