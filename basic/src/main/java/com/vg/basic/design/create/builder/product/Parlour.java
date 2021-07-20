package com.vg.basic.design.create.builder.product;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 产品：客厅
 * @author: xieweij
 * @create: 2020-12-28 14:50
 **/
@Setter
@Getter
public class Parlour {

    /**
     * 电视
     */
    private String tv;

    /**
     * 沙发
     */
    private String sofa;

    /**
     * 墙
     */
    private String wall;

    /**
     * 显示产品特性
     */
    public void show(){
        System.out.println("客厅电视:" + this.tv);
        System.out.println("客厅沙发:" + this.sofa);
        System.out.println("客厅墙:" + this.wall);

    }
}
