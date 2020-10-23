package com.vg.mybatis.app.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUserModel> {

    SysUserModel getById(Integer id);

    void insertUser(@Param("user") SysUserModel user);

    void updateUserNoVersion(@Param("user")SysUserModel user);

    void updateUserWhitVersion(@Param("user")SysUserModel user);
}
