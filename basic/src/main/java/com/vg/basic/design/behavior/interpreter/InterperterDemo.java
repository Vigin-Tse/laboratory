package com.vg.basic.design.behavior.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * 解释器模式
 * @author: xieweij
 * @time: 2021/8/2 9:34
 */
public class InterperterDemo {
    /**
     * <expression> ::= <city>的<person>
     * <city> ::= 韶关|广州
     * <person> ::= 老人|妇女|儿童
     * @param args
     */
    public static void main(String[] args){
        Context bus = new Context();
        bus.operation("韶关的老人");
        bus.operation("韶关的年轻人");
        bus.operation("广州的妇女");
        bus.operation("广州的儿童");
        bus.operation("山东的儿童");
    }
}

//抽象表达式
interface Expression{
    boolean interpret(String info); //解释方法
}

//终结符表达式类
class TerminalExpression implements  Expression{
    //用集合（Set）类来保存满足条件的城市或人，并实现抽象表达式接口中的解释方法 interpret(Stringinfo)，用来判断被分析的字符串是否是集合中的终结符。
    private Set<String> set = new HashSet<>();

    public TerminalExpression(String[] data){
        for(int i = 0; i < data.length; i++){
            set.add(data[i]);
        }
    }

    //对终结符表达式的处理
    @Override
    public boolean interpret(String info) {
        if(set.contains(info)){
            return true;
        }
        return false;
    }
}

//非终结符表达式类,含满足条件的城市的终结符表达式对象和满足条件的人员的终结符表达式对象，并实现 interpret(String info) 方法，用来判断被分析的字符串是否是满足条件的城市中的满足条件的人员。
class NonterminalExpression implements Expression{

    private Expression city = null;
    private Expression person = null;

    public NonterminalExpression(Expression city, Expression person){
        this.city = city;
        this.person = person;
    }

    //非对终结符表达式的处理
    @Override
    public boolean interpret(String info) {
        String s[] = info.split("的");
        return city.interpret(s[0]) && person.interpret(s[1]);
    }
}

//环境类,它包含解释器需要的数据，完成对终结符表达式的初始化，并定义一个方法 operation(String info) 调用表达式对象的解释方法来对被分析的字符串进行解释。
class Context{
    private String[] citys = {"韶关", "广州"};
    private String[] persons = {"老人", "妇女", "儿童"};
    private Expression cityPerson;

    public Context(){
        //数据初始化
        Expression city = new TerminalExpression(citys);
        Expression person = new TerminalExpression(persons);
        cityPerson = new NonterminalExpression(city, person);
    }

    public void operation(String info){
        //调用相关表达式的解释方法
        boolean ok = cityPerson.interpret(info);
        if(ok){
            System.out.println("您是" + info + "，您本次乘车免费！");
        }else{
            System.out.println(info + "，您不是免费人员，本次乘车扣费2元！");
        }
    }
}