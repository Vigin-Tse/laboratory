package com.vg.basic.enums;

/**
 * @Description
 * @Author xieweij
 * @create 2020/6/30 15:16
 */
public enum Animal {
    //一个成员，就是一个对象。可应用于单例
    CAT,
    DOG;

    private String name;

    Animal(){
        System.out.println("动物初始化");
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
