package com.vg.mybatis.app.demo.dao.mapper;

import com.vg.mybatis.app.demo.dao.model.SysUserModel;

public interface SysUserMapper {

    public void insertUser(SysUserModel user);
    
    public SysUserModel getById(Integer id);
    
    public void updateUser(SysUserModel user);

    public void insertUserByName(SysUserModel user);
    
}
