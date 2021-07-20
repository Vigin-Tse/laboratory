package com.vg.basic.design.create.FactoryMethod.tv;

/**
 * @description: 具体产品，sony电视
 * @author: xieweij
 * @create: 2020-11-12 16:42
 **/
public class SonyTv implements Tv {

    @Override
    public void TvInfo() {
        System.out.println("电视基本信息：sony");
    }
}
