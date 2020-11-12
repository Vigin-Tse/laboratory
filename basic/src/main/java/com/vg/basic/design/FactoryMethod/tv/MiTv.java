package com.vg.basic.design.FactoryMethod.tv;

/**
 * @description: 具体产品，小米电视
 * @author: xieweij
 * @create: 2020-11-12 16:43
 **/
public class MiTv implements Tv {

    @Override
    public void TvInfo() {
        System.out.println("电视基本信息：小米");
    }
}
