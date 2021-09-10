package com.vg.basic.string;

/**
 * @author: xieweij
 * @time: 2021/8/31 14:18
 */
public class StringEqualsDemo {
    public static void main(String[] args){

        String stra = "a";
        String newStrA1 = new String("a"); //创建2个对象，一个在堆上（String）,一个是字符串常量池中的字符串“a”对象
        String newStrA2 = new String("a"); //创建1个对象，堆上的String对象，字符串常量池中已经存在字符“a”，故不再创建对象
//        String stra = "a";                         //无创建对象

        String A1Intern = newStrA1.intern();
        String A2Intern = newStrA2.intern();

        System.out.println(stra == newStrA1);     //false
        System.out.println(stra == newStrA2);     //false
        System.out.println(newStrA1 == newStrA2); //false

        System.out.println(stra == A1Intern);     //true
        System.out.println(stra == A2Intern);     //true
        System.out.println(A1Intern == A2Intern); //true
    }

    /**
     * 创建字符串对象的方式
     */
    public void case01(){
        //创建 1 个对象
        //直接使用双引号声明出来的 String 对象会直接存储在字符串常量池中
        //会先检查字符串常量池中有没有"str1"，如果字符串常量池中没有，则创建一个，然后 str1 指向字符串常量池中的对象，如果有，则直接将 str1 指向"str1"；
        String str1 = "str1";

        //创建 2 个对象
        //1.在堆中创建一个字符串对象（str2）
        //2.检查字符串常量池中是否存在和 “str2” 的值相等的常量
        //3.没有则（在字符串常量池中）创建 “str2” 字符串常量（对象）
        String str2 = new String("str2");

    }

    /**
     * 字符串拼接
     */
    public void case02(){
        String str1 = "str";
        String str2 = "ing";

        //编译器优化（常量折叠）成 str3 = "string"，创建一个对象，放在字符串常量池
        String str3 = "str" + "ing";

        //创建2个对象，堆上的StringBuilder和String（“string”）
        //对象引用 和 “+” 的字符串拼接方式，实际上是通过 StringBuilder 调用 append() 方法实现的，拼接完成之后调用 toString() 得到一个 String 对象
        //String str4 = new StringBuilder().append(str1).append(str2).toString();
        String str4 = str1 + str2;

        //str5指向字符串常量池中（str3创建）的 “string” 字符串
        String str5 = "string";

        //创建多少个人String对象：5个，3个在堆上【 String("a") 、String("b") 、String("ab")】,2个在字符串常量池【“a” 、“b”】
        //创建了多少个对象：6个，4个在堆上【 String("a") 、String("b") 、StringBuilder 、String("ab")】,2个在字符串常量池【“a” 、“b”】
        String str6 = new String("a") + new String("b");
    }

}
