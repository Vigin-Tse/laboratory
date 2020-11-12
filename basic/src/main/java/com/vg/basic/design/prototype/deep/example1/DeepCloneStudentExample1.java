package com.vg.basic.design.prototype.deep.example1;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 深克隆成员对象类（继承cloneable方式）
 * @author: xieweij
 * @create: 2020-11-10 14:54
 **/
@Setter
@Getter
public class DeepCloneStudentExample1 implements Cloneable {

    private String name;

    private int age;

    public DeepCloneStudentExample1(String name, int age){
        this.name = name;
        this.age = age;
    }

    /**
     * 克隆对象的引用成员实现Cloneable接口并重写clone方法，引用变量也能进行克隆（开辟新内存）
     * @return
     */
    @Override
    public DeepCloneStudentExample1 clone() throws CloneNotSupportedException {
        return (DeepCloneStudentExample1)super.clone();
    }
}
