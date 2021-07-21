package com.vg.basic.extend;

public class ExtendA {

//    private static Other other2 = new Other("A-静态-成员变量对象");

    private static ExtendB other2 = new ExtendB();
    static {
        System.out.println("A-静态-代码块");
//        ExtendB b = new ExtendB();
    }

    private Other other1 = new Other("A-普通-成员变量对象");

    {
        System.out.println("A-普通-代码块");
    }

    public ExtendA(){
        System.out.println("A-构造函数");
    }
}
