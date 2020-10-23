package com.vg.mybatis.app.dao.item;

import com.vg.mybatis.app.dao.model.SysDepartmentModel;
import com.vg.mybatis.app.dao.model.SysUserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 部门用户查询
 * @author: xieweij
 * @create: 2020-10-12 15:02
 **/
@Getter
@Setter
public class DepartmentUserQueryItem extends SysDepartmentModel {

    private List<SysUserModel> users;
}
