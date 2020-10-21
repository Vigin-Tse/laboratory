package com.vg.mybatis.app.dao.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_user")
public class SysUserModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer deptId;

    private String userName;

    private Integer age;

    @Version
    private Integer version;
}
