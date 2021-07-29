package com.vg.basic.design.behavior.state;

/**
 * 状态模式
 * @author: xieweij
 * @time: 2021/7/28 10:33
 */
public class StateDemo {
    public static void main(String[] args){
        Context context = new Context(); //创建环境
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}

//环境类
class Context{
    private State state;

    //初始环境类默认状态
    public Context(){
        this.state = new ConcreteStateA();
    }

    //读取状态
    public State getState() {
        return state;
    }

    //设置新状态
    public void setState(State state) {
        this.state = state;
    }

    public void handle(){
        state.handle(this);
    }
}

//抽象状态类
abstract class State{
    public abstract void handle(Context context);
}

//具体状态A类
class ConcreteStateA extends State{

    @Override
    public void handle(Context context) {
        System.out.println("当前状态是A");
        context.setState(new ConcreteStateB());
    }
}

//具体状态B类
class ConcreteStateB extends State{

    @Override
    public void handle(Context context) {
        System.out.println("当前状态是B");
        context.setState(new ConcreteStateA());
    }
}