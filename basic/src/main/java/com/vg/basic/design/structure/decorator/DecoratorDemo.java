package com.vg.basic.design.structure.decorator;

/**
 * 装饰器模式
 * @author: xieweij
 * @time: 2021/7/21 16:23
 */
class DecoratorDemo {
    public static void main(String[] args){
        Component component = new ConcreteComponent();
        component.operation(); //单独执行构件方法

        Component d = new ConcreteDecorator(component);//具体装饰者为 component对象增加额外的功能方法
        d.operation();
    }
}

//抽象构件
interface Component{
    void operation();
}

//具体构件
class ConcreteComponent implements Component{

    @Override
    public void operation() {
        System.out.println("调用具体构件的operation方法");
    }
}

//抽象装饰
class Decorator implements Component{

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

//具体装饰角色
class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation(){
        super.operation();
        this.addedFunction();
    }

    public void addedFunction(){
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}
