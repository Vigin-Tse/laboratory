package com.vg.basic.design.structure.composite.safety;

import java.util.ArrayList;

/**
 * @author: xieweij
 * @time: 2021/7/22 16:52
 */
public class SCompositePatternDemo {
    public static void main(String[] args) {
        Composite c0 = new Composite();
        Composite c1 = new Composite();
        SComponent leaf1 = new Leaf("1");
        SComponent leaf2 = new Leaf("2");
        SComponent leaf3 = new Leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }
}

//抽象构件
interface SComponent {
    void operation();
}

//树叶构件
class Leaf implements SComponent {
    private String name;
    public Leaf(String name) {
        this.name = name;
    }
    public void operation() {
        System.out.println("树叶" + name + "：被访问！");
    }
}
//树枝构件
class Composite implements SComponent {
    private ArrayList<SComponent> children = new ArrayList<SComponent>();
    public void add(SComponent c) {
        children.add(c);
    }
    public void remove(SComponent c) {
        children.remove(c);
    }
    public SComponent getChild(int i) {
        return children.get(i);
    }
    public void operation() {
        for (Object obj : children) {
            ((SComponent) obj).operation();
        }
    }
}