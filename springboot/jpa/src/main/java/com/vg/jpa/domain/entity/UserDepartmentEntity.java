package com.vg.jpa.domain.entity;

import lombok.Data;

/**
 * @description: 用户部门信息实体类
 * @author: xieweij
 * @create: 2020-10-23 16:29
 **/
@Data
public class UserDepartmentEntity {

    private Integer id;

    private String userName;

    private String deptName;

    public UserDepartmentEntity(Integer id, String userName, String deptName){
        this.id = id;
        this.userName = userName;
        this.deptName = deptName;
    }
}
