package com.vg.basic.temp.inner;

import com.vg.basic.temp.FClass;
import com.vg.basic.temp.Finterface;

/**
 * @author: xieweij
 * @time: 2021/7/1 14:28
 */
public class InnerClass01 extends FClass implements Finterface{

    private String innerClassStr = "子类私有成员变量";

    @Override
    public String FinterfaceMethod() {
        return "子类方法输出：" + innerClassStr;
    }

    public String FinterfaceDefault(){
        return "重写父类默认方法方法输出：" + innerClassStr;
    }

    public String getClassStrInfo(){
        return "子类方法输出：" + innerClassStr;
    }

    public String InnerClass01Method(){
        return "子类 独有方法";
    }
}
