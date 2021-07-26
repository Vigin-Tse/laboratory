package com.vg.basic.design.behavior.templateMethod;

/** 抽象模板方法
 * @author: xieweij
 * @time: 2021/7/23 14:07
 */
class TemplateMethodDemo {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.templateMethod();
    }
}

//抽象类
abstract class AbstractClass{

    //模板方法
    public void templateMethod(){
        this.specificMethod();
        this.abstractMethod1();
        this.abstractMethod2();
    }

    //抽象类具体方法
    public void specificMethod(){
        System.out.println("抽象类中的具体方法被调用");
    }

    //抽象方法1
    public abstract void abstractMethod1();

    //抽象方法2
    public abstract void abstractMethod2();

}

//具体子类
class ConcreteClass extends AbstractClass{

    @Override
    public void abstractMethod1() {
        System.out.println("具体方法1被调用");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("具体方法2被调用");
    }
}