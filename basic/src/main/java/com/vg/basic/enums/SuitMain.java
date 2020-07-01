package com.vg.basic.enums;

/**
 * @Description
 * @Author xieweij
 * @create 2020/6/30 15:21
 */
public class SuitMain {
    public static void main(String[] args){
        System.out.println(Animal.CAT.getName());
        System.out.println(Animal.DOG.getName());

        Animal.CAT.setName("i am cat");
        Animal.DOG.setName("i am dog");

        System.out.println(Animal.CAT.getName());
        System.out.println(Animal.DOG.getName());
    }
}
