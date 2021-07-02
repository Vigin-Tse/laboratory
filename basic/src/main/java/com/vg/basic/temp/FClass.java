package com.vg.basic.temp;

/**
 * @author: xieweij
 * @time: 2021/7/1 14:33
 */
public class FClass {

    public String fclassStr = "父类公有成员变量";

    public String FMethod(){
        return "父类独有方法";
    }

    public String getClassStrInfo(){
        return "父类方法输出：" + fclassStr;
    }
}
