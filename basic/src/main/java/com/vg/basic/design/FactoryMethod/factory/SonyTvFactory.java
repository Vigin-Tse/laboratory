package com.vg.basic.design.FactoryMethod.factory;

import com.vg.basic.design.FactoryMethod.tv.SonyTv;
import com.vg.basic.design.FactoryMethod.tv.Tv;

/**
 * @description: 具体工厂，sony电视工厂
 * @author: xieweij
 * @create: 2020-11-12 16:46
 **/
public class SonyTvFactory implements TvFactory {

    @Override
    public Tv createTv() {
        return new SonyTv();
    }
}
