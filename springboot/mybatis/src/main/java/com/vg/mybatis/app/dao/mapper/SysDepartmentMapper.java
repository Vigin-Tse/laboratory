package com.vg.mybatis.app.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vg.mybatis.app.dao.item.DepartmentUserQueryItem;
import com.vg.mybatis.app.dao.item.UserDepartmentQueryItem;
import com.vg.mybatis.app.dao.model.SysDepartmentModel;

import java.util.List;

public interface SysDepartmentMapper extends BaseMapper<SysDepartmentModel> {

    UserDepartmentQueryItem oneToOneQuery(Integer userId);

    List<DepartmentUserQueryItem> oneToManyQuery(Integer deptId);

    List<DepartmentUserQueryItem> manyToManyQuery();
}
