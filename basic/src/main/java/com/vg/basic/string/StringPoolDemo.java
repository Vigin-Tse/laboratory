package com.vg.basic.string;

/**
 * @author: xieweij
 * @time: 2021/7/7 10:24
 */
public class StringPoolDemo {



    public void case01(){

        String s1 = new String("case01");
        String s2 = "case01";
        String s3 = "case" + "01";

        System.out.println(s1 == s2);          //false
        System.out.println(s1.intern() == s2); //true
        System.out.println(s3 == s2);          //true
    }


    /**
     * jdk 1.6 与 1.7的比较
     */
    public void case02(){
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 ==s2);


        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);

        //结果
        //jdk6 下   false false
        //jdk7及以上 false true
    }

    /**
     * 基于case02 把intern()下调一行
     */
    public void case03(){
        String s1 = new String("1");
        String s2 = "1";
        s1.intern();
        System.out.println(s1 ==s2);


        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);

        //结果
        //jdk6 下   false false
        //jdk7及以上 false false
    }

    //从case02、case03看出：
    //jdk7及之后版本 intern 操作和常量池都做了一定的修改。主要包括2点：
    //1. 将String常量池 从 Perm 区移动到了 Java Heap区
    //2. String#intern 方法时，如果存在堆中的对象，会直接保存对象的引用，而不会重新创建对象。

    public static void main(String[] args){
        StringPoolDemo demo = new StringPoolDemo();

        demo.case01();
//        demo.case02();
//        demo.case03();
    }


}
