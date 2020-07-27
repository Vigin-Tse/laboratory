package com.vg.springmvc.mapper;

import com.vg.springmvc.model.SysUserModel;

public interface SysUserMapper {

    public void insertUser(SysUserModel user);
    
    public SysUserModel getById(Integer id);
    
    public void updateUser(SysUserModel user);

    public void insertUserByName(SysUserModel user);
    
}
