package com.vg.basic.design.structure.adapter.objAdapter;

//客户端代码
class ObjAdapterDemo {
    public static void main(String[] args){}
}

//目标接口
interface ObjTarget{

    void request();
}

//适配者
class ObjAdaptee{
    public void specificRequest(){
        System.out.println("适配者中的业务代码被调用！");
    }
}

//对象适配器类
class ObjAdapter implements ObjTarget{

    private ObjAdaptee objAdaptee;

    public ObjAdapter(ObjAdaptee objAdaptee){
        this.objAdaptee = objAdaptee;
    }

    @Override
    public void request() {
        this.objAdaptee.specificRequest();
    }
}