package com.vg.basic.design.structure.bridge;

/** 桥接模式示例
 * @author: xieweij
 * @time: 2021/7/21 11:42
 */
class BridgeDemo {
    public static void main(String[] args){
        Implementor implementor = new ConcreteImplementorA();

        Abstraction abs = new RefinedAbstraction(implementor);
        abs.operation();
    }
}


//实现化角色
interface Implementor{
    void operationImpl();
}

//具体实现化角色
class ConcreteImplementorA implements Implementor{

    @Override
    public void operationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}

//抽象化角色
abstract class Abstraction{

    protected Implementor implementor;

    protected Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    public abstract void operation();
}

//扩张抽象化角色
class RefinedAbstraction extends Abstraction{
    protected RefinedAbstraction(Implementor implementor){
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        implementor.operationImpl();
    }
}