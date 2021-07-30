package com.vg.basic.design.behavior.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 访问者模式
 * @author: xieweij
 * @time: 2021/7/30 11:15
 */
public class VisitorDemo {
    public static void mian(String[] args){
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());

        Visitor visitor = new ConcreteVistorA();
        os.accept(visitor);

        visitor = new ConcreteVistorB();
        os.accept(visitor);
    }
}

//抽象访问者
interface Visitor {

    void visit(ConcreteElementA element);

    void visit(ConcreteElementB element);
}

//具体访问者A
class ConcreteVistorA implements Visitor{

    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者A访问-->" + element.operationA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者A访问-->" + element.operationB());
    }
}

//具体访问者B
class ConcreteVistorB implements Visitor{

    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("具体访问者B访问-->" + element.operationA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("具体访问者B访问-->" + element.operationB());
    }
}

//抽象元素类
interface Element{
    void accapt(Visitor visitor);
}

//具体元素类A
class ConcreteElementA implements Element{

    @Override
    public void accapt(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA(){
        return "具体元素A的操作";
    }
}

//具体元素类B
class ConcreteElementB implements Element{

    @Override
    public void accapt(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB(){
        return "具体元素B的操作";
    }
}

//对象结构角色
class ObjectStructure{

    private List<Element> list = new ArrayList<>();

    public void accept(Visitor visitor){
        Iterator<Element> i = list.iterator();
        while(i.hasNext()){
            i.next().accapt(visitor);
        }
    }

    public void add(Element element){
        list.add(element);
    }

    public void remove(Element element){
        list.remove(element);
    }
}