package com.vg.basic.temp.inner;

import com.vg.basic.temp.FClass;
import com.vg.basic.temp.Finterface;

/**
 * @author: xieweij
 * @time: 2021/7/1 14:29
 */
public class TestMain {

    static public void main(String[] args){
        InnerClass01 inner = new InnerClass01();
        System.out.println(inner.FinterfaceDefault());

        Finterface finterface = new InnerClass01();
        System.out.println(finterface.FinterfaceMethod());
        System.out.println(finterface.FinterfaceDefault());


//        FClass fClass = new InnerClass01();
//        System.out.println(fClass.fclassStr);
//        System.out.println(fClass.FMethod());
//        System.out.println(fClass.getClassStrInfo());
    }
}
