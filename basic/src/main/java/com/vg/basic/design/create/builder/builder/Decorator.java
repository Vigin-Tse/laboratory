package com.vg.basic.design.create.builder.builder;

import com.vg.basic.design.create.builder.product.Parlour;

/**
 * @description: 抽象建造者：装修工
 * @author: xieweij
 * @create: 2020-12-28 14:56
 **/
public abstract class Decorator {

    //创建产品
    protected Parlour product = new Parlour();

    public abstract void buildTv();

    public abstract void buildSofa();

    public abstract void buildWall();

    //返回产品对象
    public Parlour getParlour(){
        return this.product;
    }
}
