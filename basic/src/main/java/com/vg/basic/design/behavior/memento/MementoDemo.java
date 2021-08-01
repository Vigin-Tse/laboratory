package com.vg.basic.design.behavior.memento;

/**
 * 备忘录模式
 * @author: xieweij
 * @time: 2021/7/30 17:01
 */
public class MementoDemo {
    public static void main(String[] args){
        Originator or = new Originator();
        Caretaker cr = new Caretaker();

        or.setState("S0"); //初始状态
        cr.setMemento(or.createMemento()); //保存状态

        or.setState("S1"); //新的状态

        or.restoreMemento(cr.getMemento()); //恢复旧状态
    }
}

//备忘录
class Memento{
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

//发起人
class Originator{
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento(){
        return new Memento(state);
    }

    //回滚
    public void restoreMemento(Memento m){
        this.setState(m.getState());
    }
}

//管理者
class Caretaker{
    private Memento memento;

    public void setMemento(Memento m){
        memento = m;
    }

    public Memento getMemento(){
        return memento;
    }
}
