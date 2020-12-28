package com.vg.basic.design.builder.builder;

/**
 * @description: 具体建造者：装修工1
 * @author: xieweij
 * @create: 2020-12-28 15:01
 **/
public class ConcreteDecorator1 extends Decorator {


    @Override
    public void buildTv() {
        //实现（具体）构建部件（电视）逻辑
        //todo

        super.product.setTv("康佳电视");
    }

    @Override
    public void buildSofa() {
        //实现（具体）构建部件（沙发）逻辑
        //todo

        super.product.setSofa("宜家沙发");
    }

    @Override
    public void buildWall() {
        //实现（具体）构建部件（墙）逻辑
        //todo

        super.product.setWall("立邦墙漆");
    }
}

