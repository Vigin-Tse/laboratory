package com.vg.basic.design.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代模式
 * @author: xieweij
 * @time: 2021/7/29 15:26
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("聚合的内容有：");
        Iterator it = ag.getIterator();
        while (it.hasNext()) {
            Object ob = it.next();
            System.out.print(ob.toString() + "\t");
        }
        Object ob = it.first();
        System.out.println("\nFirst：" + ob.toString());
    }
}

//抽象聚合类
interface Aggregate{

    void add(Object obj);

    void remove(Object obj);

    Iterator getIterator();
}

//抽象迭代器
interface Iterator{

    Object first();

    Object next();

    boolean hasNext();
}

//具体聚合类
class ConcreteAggregate implements Aggregate{

    private List<Object> list = new ArrayList<>();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}

//具体迭代器
class ConcreteIterator implements Iterator{

    private List<Object> list = null;
    private int index = -1;
    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object first() {
        index = 0;
        Object obj = list.get(index);
        return obj;
    }

    @Override
    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(++index);
        }
        return obj;
    }
}