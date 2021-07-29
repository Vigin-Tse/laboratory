package com.vg.basic.design.behavior.chain;

/**
 * 责任链模式
 * @author: xieweij
 * @time: 2021/7/27 15:17
 */
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        //组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handleRequest("two");
    }
}

//抽象处理者角色
abstract class Handler{

    //定义下一个处理者
    private Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handleRequest(String request);
}

//具体处理者角色1
class ConcreteHandler1 extends Handler{

    @Override
    public void handleRequest(String request) {
        if (request.equals("one")){
            System.out.println("具体处理者1的处理");
        }else{
            if (getNext() != null){
                getNext().handleRequest(request);
            }else{
                System.out.println("没有人处理该请求！");
            }
        }
    }
}

//具体处理者角色2
class ConcreteHandler2 extends Handler{

    @Override
    public void handleRequest(String request) {
        if (request.equals("two")){
            System.out.println("具体处理者2的处理");
        }else{
            if (getNext() != null){
                getNext().handleRequest(request);
            }else{
                System.out.println("没有人处理该请求！");
            }
        }
    }
}
