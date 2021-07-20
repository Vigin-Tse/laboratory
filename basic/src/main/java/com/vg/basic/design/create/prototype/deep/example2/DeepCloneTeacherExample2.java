package com.vg.basic.design.create.prototype.deep.example2;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * @description: 深克隆（序列化方式）
 * @author: xieweij
 * @create: 2020-11-12 10:11
 **/
@Getter
@Setter
public class DeepCloneTeacherExample2 implements Serializable {

    private String name;

    private int age;

    private DeepCloneStudentExample2 student;

    public DeepCloneTeacherExample2(String name, int age,  DeepCloneStudentExample2 student){
        this.name = name;
        this.age = age;
        this.student = student;
    }

    /**
     * 序列化克隆方式
     * @return
     */
    public DeepCloneTeacherExample2 clone(){
        DeepCloneTeacherExample2 teacher = null;

        try {
            // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();


            //将流反序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            teacher = (DeepCloneTeacherExample2) ois.readObject();

            ois.close();
            baos.close();
            bais.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    public static void main(String[] args){
        DeepCloneStudentExample2 stu = new DeepCloneStudentExample2("小李", 12);
        DeepCloneTeacherExample2 tecLiu = new DeepCloneTeacherExample2("刘老师", 28, stu);

        DeepCloneTeacherExample2 tecLiu2 = tecLiu.clone();

        System.out.println(JSON.toJSONString(tecLiu));
        System.out.println(JSON.toJSONString(tecLiu2));
        System.out.println("（内存空间是否一样）老师是否同一个：" + (tecLiu == tecLiu2));
        System.out.println("（内存空间是否一样）学生是否同一个：" + (tecLiu.getStudent() == tecLiu2.getStudent()));
    }
}
