package com.vg.basic.design.FactoryMethod;

import com.vg.basic.design.FactoryMethod.factory.MiTvFactory;
import com.vg.basic.design.FactoryMethod.factory.SonyTvFactory;
import com.vg.basic.design.FactoryMethod.factory.TvFactory;
import com.vg.basic.design.FactoryMethod.tv.Tv;

/**
 * @description: 工厂方法模式例子
 * @author: xieweij
 * @create: 2020-11-12 15:54
 **/
public class FactoryMethodTest {


    public static void main(String[] args){

        Tv tv;
        TvFactory factory;

        //创建sony电视
        factory = new SonyTvFactory();
        tv = factory.createTv();
        tv.TvInfo();

        //创建小米电视
        factory = new MiTvFactory();
        tv = factory.createTv();
        tv.TvInfo();

    }
}
