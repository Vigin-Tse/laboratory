package com.vg.shareexample;

public class ShareDemo {

    private String name;

    public ShareDemo(){
        System.out.println("ShareDemo bean init");
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void doPrintlnName(){
        System.out.println(this.name);
    }
}
