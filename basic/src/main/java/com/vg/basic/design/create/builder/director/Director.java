package com.vg.basic.design.create.builder.director;

import com.vg.basic.design.create.builder.builder.Decorator;
import com.vg.basic.design.create.builder.product.Parlour;

/**
 * @description: 指挥者：调用建造者中的方法完成复杂对象的创建
 * @author: xieweij
 * @create: 2020-12-28 15:15
 **/
public class Director {

    private Decorator builder;

    public Director(Decorator builder){
        this.builder = builder;
    }

    /**
     * 产品构建与组装方法
     * @return
     */
    public Parlour construct(){
        builder.buildTv();
        builder.buildSofa();
        builder.buildWall();

        return builder.getParlour();
    }
}
