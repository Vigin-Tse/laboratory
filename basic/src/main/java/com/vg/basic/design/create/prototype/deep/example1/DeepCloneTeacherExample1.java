package com.vg.basic.design.create.prototype.deep.example1;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 深克隆（实现cloneable方式）
 * @author: xieweij
 * @create: 2020-11-10 14:57
 **/
@Setter
@Getter
public class DeepCloneTeacherExample1 implements Cloneable {

    private String name;

    private int age;

    private DeepCloneStudentExample1 student;

    public DeepCloneTeacherExample1(String name, int age,  DeepCloneStudentExample1 student){
        this.name = name;
        this.age = age;
        this.student = student;
    }

    @Override
    public DeepCloneTeacherExample1 clone() throws CloneNotSupportedException {
        System.out.println("老师被复制了");
        DeepCloneTeacherExample1 t = null;
        t = (DeepCloneTeacherExample1)super.clone();
        t.student = (DeepCloneStudentExample1) this.student.clone();
        return t;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepCloneStudentExample1 student1 = new DeepCloneStudentExample1("小明", 18);
        DeepCloneTeacherExample1 teacher1 = new DeepCloneTeacherExample1("李老师", 32, student1);

        DeepCloneTeacherExample1 teacher2 = teacher1.clone();
        System.out.println("引用成员共用同一内存：" + (teacher1.getStudent() == teacher2.getStudent()));
    }
}
