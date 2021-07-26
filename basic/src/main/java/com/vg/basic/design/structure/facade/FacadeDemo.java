package com.vg.basic.design.structure.facade;

/**
 * 外观模式
 * @author: xieweij
 * @time: 2021/7/22 10:23
 */
class FacadeDemo {
    public static void main(String[] args){
        Facade f = new Facade();
        f.method();
    }
}

//外观角色
class Facade{
    private SubSystem01 sys01 = new SubSystem01();
    private SubSystem02 sys02 = new SubSystem02();
    private SubSystem03 sys03 = new SubSystem03();

    public void method(){
        sys01.method();
        sys02.method();
        sys03.method();
    }
}

//子系统01
class SubSystem01{
    public void method(){
        System.out.println("子系统01的接口方法");
    }
}

//子系统02
class SubSystem02{
    public void method(){
        System.out.println("子系统02的接口方法");
    }
}

//子系统03
class SubSystem03{
    public void method(){
        System.out.println("子系统03的接口方法");
    }
}