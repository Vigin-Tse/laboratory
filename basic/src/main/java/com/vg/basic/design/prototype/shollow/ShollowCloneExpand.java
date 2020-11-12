package com.vg.basic.design.prototype.shollow;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 浅克隆成员对象
 * @author: xieweij
 * @create: 2020-11-06 16:40
 **/
@Getter
@Setter
public class ShollowCloneExpand {

    /**
     * 街道
     */
    private String address;

    /**
     * 门牌号
     */
    private int no;

    public ShollowCloneExpand(String address, int no){
        System.out.println("地址信息构造方法调用");
        this.address = address;
        this.no = no;
    }

}
