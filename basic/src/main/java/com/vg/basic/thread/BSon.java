package com.vg.basic.thread;

/**
 * @author: xieweij
 * @time: 2021/12/7 16:05
 */
public class BSon {

    public static int a;
    static {
        System.out.println("BSon static block");
    }

    {
        System.out.println("BSon block");
    }

    public BSon(){
        System.out.println("BSon init");
    }
}
