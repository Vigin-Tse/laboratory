package com.vg.basic.inner.Member;

/**
 * @Description
 * @Author xieweij
 * @create 2020/6/10 10:26
 */
public class MemberTest {

    /**
     * 内部类可以拥有 private 访问权限、protected 访问权限、public 访问权限及包访问权限。
     * 比如下面面的例子，如果成员内部类 Inner
     * 1. private 修饰，则只能在外部类的内部访问，
     * 2. public 修饰，则任何地方都能访问；
     * 3. protected 修饰，则只能在同一个包下或者继承外部类的情况下访问；
     * 4. 默认访问权限，则只能在同一个包下访问。
     * 和外部类有一点不一样，外部类只能被 public 和 默认 两种权限修饰。
     */
    public static void main(String[] args){

        //成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。
        //方式一：
        MemberOutter outter = new MemberOutter();
        MemberOutter.MemberInner inner = outter.new MemberInner();

        //方式二：
        MemberOutter.MemberInner inner2 = outter.getInstance();
    }
}