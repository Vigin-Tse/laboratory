package com.vg.basic.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * @author: xieweij
 * @time: 2021/7/28 13:41
 */
public class ObserverDemo {
    public static void main(String[] args){
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserve1();
        Observer obs2 = new ConcreteObserve2();

        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }
}

//抽象目标
abstract class Subject{
    protected List<Observer> observers = new ArrayList<>();

    //增加观察者方法
    public void add(Observer observer){
        observers.add(observer);
    }

    //删除观察者方法
    public void remove(Observer observer){
        observers.remove(observer);
    }

    //通知观察者方法
    public abstract void notifyObserver();
}

//具体目标
class ConcreteSubject extends Subject{

    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生了变化");
        for(Observer obs : observers){
            obs.response();
        }
    }
}

//抽象观察者
interface Observer{
    //反应
    void response();
}

//具体观察者1
class ConcreteObserve1 implements Observer{

    @Override
    public void response() {
        System.out.println("具体观察者1做出反应");
    }
}

//具体观察者2
class ConcreteObserve2 implements Observer{

    @Override
    public void response() {
        System.out.println("具体观察者2做出反应");
    }
}


