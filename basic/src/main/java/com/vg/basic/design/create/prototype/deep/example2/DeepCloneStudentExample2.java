package com.vg.basic.design.create.prototype.deep.example2;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 深克隆实现（序列化方式）
 * @author: xieweij
 * @create: 2020-11-12 10:04
 **/
@Getter
@Setter
public class DeepCloneStudentExample2 implements Serializable {

    private String name;

    private int age;

    public DeepCloneStudentExample2(String name, int age){
        this.name = name;
        this.age = age;
    }
}
