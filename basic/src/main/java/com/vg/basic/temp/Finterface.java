package com.vg.basic.temp;

/**
 * @author: xieweij
 * @time: 2021/7/1 14:26
 */
public interface Finterface {

    String FinterfaceStr = "FinterfaceStr";

    String FinterfaceMethod();

    default String FinterfaceDefault(){return "父类接口默认方法" + FinterfaceStr;}
}
