package com.vg.basic.string;

/**
 * @author: xieweij
 * @time: 2021/12/23 14:22
 */
public class StringAdrTest {

    public static void main(String[] args){
//        StringAdrTest test = new StringAdrTest();
//        test.case01();
        String s1 = "计算机";
        String s2 = s1.intern();
        System.out.println(s1 == s2);
    }

    public void case01(){
        String s = new String("abc");   //(1)
        String s1 = "abc"; 			//(2)
        String s2 = new String("abc");		//(3)

        System.out.println(s == s1.intern());	//(4)
        System.out.println(s == s2.intern());//(5)

        System.out.println(s1 == s2.intern());//(6)
        System.out.println(s1 == s.intern());
        System.out.println(s.intern() == s2.intern());
    }
}
