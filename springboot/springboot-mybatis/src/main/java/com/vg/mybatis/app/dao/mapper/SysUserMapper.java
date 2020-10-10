package com.vg.mybatis.app.dao.mapper;

import com.vg.mybatis.app.dao.model.SysUserModel;

public interface SysUserMapper {

    public void insertUser(SysUserModel user);
    
    public SysUserModel getById(Integer id);
    
    public void updateUser(SysUserModel user);

    public void insertUserByName(SysUserModel user);
    
}
