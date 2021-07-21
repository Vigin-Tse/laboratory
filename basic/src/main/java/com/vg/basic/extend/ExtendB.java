package com.vg.basic.extend;

public class ExtendB extends ExtendA {
    
    {
        System.out.println("B-普通-代码块");
    }

    private Other other1 = new Other("B-普通-成员变量对象");

    private static String bStr = "B static string";
    private static Other other2 = new Other("B-静态-成员变量对象");

    static {
        System.out.println("B-静态-代码块");
    }

    public ExtendB(){
        System.out.println("B-构造函数");
        System.out.println(bStr);

    }
}
