package com.vg.basic.design.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式
 * @author: xieweij
 * @time: 2021/7/28 16:33
 */
public class MediatorDemo {
    public static void main(String[] args){
        Mediator md = new ConcreteMediator();
        Colleague c1 = new ConcreteColleaguel();
        Colleague c2 = new ConcreteColleague2();

        md.register(c1);
        md.register(c2);

        c1.send();
        c2.send();
    }
}

//抽象中介者
abstract class Mediator{
    public abstract void register(Colleague colleague); //注册

    public abstract void relay(Colleague cl);//转发

}

//抽象同事类
abstract class Colleague{
    protected Mediator mediator;

    public void setMediator(Mediator mediator){
        this.mediator = mediator;
    }

    public abstract void receive();

    public abstract void send();
}

//具体中介者
class ConcreteMediator extends Mediator{

    private List<Colleague> colleagues = new ArrayList<>();

    //同事类向该中介注册
    @Override
    public void register(Colleague colleague) {
        if(!colleagues.contains(colleague)){
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    //消息转发
    @Override
    public void relay(Colleague cl) {
        for (Colleague ob : colleagues){
            if (! ob.equals(cl)){
                ob.receive();
            }
        }
    }
}

//具体同事类1
class ConcreteColleaguel extends Colleague{
    @Override
    public void receive() {
        System.out.println("具体同事类1收到请求");
    }

    @Override
    public void send() {
        System.out.println("具体同事类1发出请求");
        mediator.relay(this);
    }
}

//具体同事类2
//具体同事类1
class ConcreteColleague2 extends Colleague{
    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2发出请求");
        mediator.relay(this);
    }
}