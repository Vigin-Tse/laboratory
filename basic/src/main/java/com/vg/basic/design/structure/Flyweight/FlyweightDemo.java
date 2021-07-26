package com.vg.basic.design.structure.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * @author: xieweij
 * @time: 2021/7/22 13:50
 */
class FlyweightDemo {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight tony01 = factory.getFlyweight("tony");
        Flyweight tony02 = factory.getFlyweight("tony");
        Flyweight bill = factory.getFlyweight("bill");

        System.out.println(tony01 == tony02);

        tony01.operation(new UnshareConcreteFlyweight("传入非共享单元参数-a"));
        tony02.operation(new UnshareConcreteFlyweight("传入非共享单元参数-b"));
    }
}

//非享元角色，里面包含了非共享的外部状态信息info
class UnshareConcreteFlyweight{

    //非共享的外部状态信息
    private String info;

    public UnshareConcreteFlyweight(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

//抽象享元角色，里面包含了享元方法 operation(UnsharedConcreteFlyweight state)，非享元的外部状态以参数的形式通过该方法传入
interface Flyweight{
    void operation(UnshareConcreteFlyweight state);
}

//具体享元角色，包含了关键字 key，它实现了抽象享元接口
class ConcreteFlyweight implements Flyweight{

    private String key;

    public ConcreteFlyweight(String key){
        this.key = key;
        System.out.println("具体享元" + key + "被创建！");
    }

    @Override
    public void operation(UnshareConcreteFlyweight state) {
        System.out.print("具体享元" + key + "被调用，");
        System.out.println("非享元信息是:" + state.getInfo());
    }
}

//享元工厂角色，负责创建和管理享元角色。当客户对象请求一个享元对象时，享元工厂检査系统中是否存在符合要求的享元对象，如果存在则提供给客户；如果不存在的话，则创建一个新的享元对象。
class FlyweightFactory{

    private Map<String, Flyweight> flyweightsMap = new HashMap<>();

    public Flyweight getFlyweight(String key){
        Flyweight flyweight = flyweightsMap.get(key);
        if (flyweight != null){
            System.out.println("具体享元" + key + "已经存在，被成功获取！");
        }else{
            flyweight = new ConcreteFlyweight(key);
            flyweightsMap.put(key, flyweight);
        }
        return flyweight;
    }
}
