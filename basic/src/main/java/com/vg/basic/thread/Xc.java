package com.vg.basic.thread;

/**
 * @author: xieweij
 * @time: 2021/12/7 15:53
 */
public class Xc {



    static {
        BSon son = new BSon();
    }

    private int i;

    public Xc(){
        this.i = 11;
    }

    public Xc(int i){
        this.i = i;
    }

    public static void main(String[] args){
        Xc xc = new Xc();
    }
}
