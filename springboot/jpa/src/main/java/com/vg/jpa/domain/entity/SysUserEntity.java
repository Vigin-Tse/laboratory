package com.vg.jpa.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xieweij
 * @time: 2021/12/24 9:47
 */
@Data
public class SysUserEntity implements Serializable {
    private Integer id;

    private String uName;

    public SysUserEntity(Integer id, String uName){
        this.id = id;
        this.uName = uName;
    }
}
