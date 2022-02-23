package com.vg.basic.thread;

/**
 * @author:
 * @time: 2021/12/7 15:53
 */
public class Xc {



    static {
        System.out.println("Xc static block");
        BSon son = new BSon();
    }

    private int i;

    public Xc(){
        this.i = 11;
    }

    public Xc(int i){
        this.i = i;
    }

    {
        System.out.println("Xc block");
    }

    private int a = 10;

    public static void main(String[] args){
        Xc xc = new Xc();
    }
}
