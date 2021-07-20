package com.vg.basic.design.structure.adapter.clazAdapter;

//客户端代码
public class ClazAdapterDemo {
    public static void main(String[] args){
        ClazTarget target = new ClazAdapter();
        target.request();
    }
}

//目标接口
interface ClazTarget {

    void request();
}

//适配者
class ClazAdaptee{
    public void specificRequest(){
        System.out.println("适配者中的业务代码被调用！");
    }
}

//类适配器
class ClazAdapter extends ClazAdaptee implements ClazTarget{

    @Override
    public void request() {
        this.specificRequest();
    }
}