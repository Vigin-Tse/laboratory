package com.vg.mybatis.app.dao.mapper;

import com.vg.mybatis.app.dao.item.DepartmentUserQueryItem;
import com.vg.mybatis.app.dao.item.UserDepartmentQueryItem;

import java.util.List;

public interface SysDepartmentMapper {

    UserDepartmentQueryItem oneToOneQuery(Integer userId);

    List<DepartmentUserQueryItem> oneToManyQuery(Integer deptId);

    List<DepartmentUserQueryItem> manyToManyQuery();
}
