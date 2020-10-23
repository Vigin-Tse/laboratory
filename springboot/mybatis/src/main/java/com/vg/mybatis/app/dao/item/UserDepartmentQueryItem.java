package com.vg.mybatis.app.dao.item;

import com.vg.mybatis.app.dao.model.SysDepartmentModel;
import com.vg.mybatis.app.dao.model.SysUserModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 用户部门查询
 * @author: xieweij
 * @create: 2020-10-10 17:09
 **/
@Getter
@Setter
public class UserDepartmentQueryItem extends SysUserModel {

    private SysDepartmentModel department;
}
