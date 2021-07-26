package com.vg.basic.design.behavior.command;

/** 命令模式
 * @author: xieweij
 * @time: 2021/7/26 16:27
 */
class CommandDemo {
    public static void main(String[] args){
        Command cmd = new ConcreteCommand();

        Invoker invoker = new Invoker(cmd);
        invoker.call();
    }
}

//调用者
class Invoker{
    private Command command;

    public Invoker(Command command){
        this.command = command;
    }

    public void setCommand(Command command){
        this.command = command;
    }

    public void call(){
        System.out.println("调用者执行命令command");
        command.execute();
    }
}

//抽象命令
interface Command{
    void execute();
}

//具体命令
class ConcreteCommand implements Command{

    private Receiver receiver;

    ConcreteCommand(){
        this.receiver = new Receiver();
    }

    @Override
    public void execute() {
        this.receiver.action();
    }
}

//接收者
class Receiver{
    public void action(){
        System.out.println("接收者的action()方法被调用...");
    }
}