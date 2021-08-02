package com.vg.basic.design.behavior.strategy;

/**
 * 策略模式
 * @author: xieweij
 * @time: 2021/7/26 10:37
 */
class StrategyDemo {
    public static void main(String[] args){
        Context context = new Context();

        //执行策略A
        Strategy strategy = new ConcreteStrategyA();
        context.setStrategy(strategy);
        context.strategyMethod();

        //执行策略B
        strategy = new ConcreteStrategyB();
        context.setStrategy(strategy);
        context.strategyMethod();
    }
}

//抽象策略类
interface Strategy{
    //策略方法
    void strategyMethod();
}

//具体策略类A
class ConcreteStrategyA implements Strategy{

    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}

//具体策略类B
class ConcreteStrategyB implements Strategy{

    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}

//环境类
class Context{
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }
}