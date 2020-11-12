package com.vg.basic.design.prototype.shollow;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 浅克隆
 * @author: xieweij
 * @create: 2020-11-06 13:47
 **/
@Getter
@Setter
public class ShollowClone implements Cloneable {

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 地址信息
     */
    private ShollowCloneExpand address;

    public ShollowClone(String name, int age, ShollowCloneExpand address){
        System.out.println("个人基本信息构造方法调用");
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public ShollowClone clone() throws CloneNotSupportedException{
        System.out.println("peter被复制了");
        return (ShollowClone) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * peter 个人信息
         */
        ShollowCloneExpand peterAddress = new ShollowCloneExpand("广州越秀东风路", 115);
        ShollowClone peter = new ShollowClone("peter", 18, peterAddress);

        /**
         * 复制 peter哥哥 个人信息
         */
        ShollowClone peterBigBrother = peter.clone();
        peterBigBrother.setName("bigBrother");
        peterBigBrother.setAge(19);
        peterBigBrother.getAddress().setAddress("广州越秀建设南路");
        peterBigBrother.getAddress().setNo(30);

        System.out.println("===================复制结果===================");
        System.out.println("是否同一个人：" + (peter == peterBigBrother) + "-----新对象引用与旧对象引用是否值指向同一内存地址");
        System.out.println("哥哥年纪比peter大" +   "，哥哥（" + peterBigBrother.getAge() + ")，peter(" + peter.getAge() + ")：" +(peterBigBrother.getAge() > peter.getAge()) + "-----修改新对象的基本属性，并不会影响原对象的基本属性");
        System.out.println("弟弟随哥哥搬家了，新地址=" + peter.getAddress().getAddress() + peter.getAddress().getNo() + "：" + (peter.getAddress() == peterBigBrother.getAddress()) + "-----非基本类型属性，仍指向原有属性所指向的对象的内存地址");
    }

}
