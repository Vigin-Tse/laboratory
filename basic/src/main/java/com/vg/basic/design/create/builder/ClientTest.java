package com.vg.basic.design.create.builder;

import com.vg.basic.design.create.builder.builder.ConcreteDecorator1;
import com.vg.basic.design.create.builder.builder.Decorator;
import com.vg.basic.design.create.builder.director.Director;
import com.vg.basic.design.create.builder.product.Parlour;

/**
 * @description: 建造者模式测试
 * @author: xieweij
 * @create: 2020-12-28 15:21
 **/
public class ClientTest {

    public static void main(String[] args){
        //创建装修工1（建造者）
        Decorator builder = new ConcreteDecorator1();

        //创建指挥者，并制定装修工（建造者）
        Director director = new Director(builder);

        //创建产品
        Parlour product = director.construct();
        product.show();
    }
}
