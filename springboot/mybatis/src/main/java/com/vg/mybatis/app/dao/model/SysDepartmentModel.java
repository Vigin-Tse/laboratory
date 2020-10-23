package com.vg.mybatis.app.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 部门
 * @author: xieweij
 * @create: 2020-10-10 16:56
 **/
@Setter
@Getter
@TableName("sys_department")
public class SysDepartmentModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String deptName;

    @Version
    private Integer version;
}
