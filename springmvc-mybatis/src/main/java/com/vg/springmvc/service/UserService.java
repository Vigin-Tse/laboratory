package com.vg.springmvc.service;

import com.vg.springmvc.model.SysUserModel;

public interface UserService {

    SysUserModel getUserById(Integer id);

    void addUser(SysUserModel user);    
    
    void updateUser(SysUserModel user);
    
    /**
     * 测试事务
     * id=1,age-1
     * id=2,age+1
     * */
    void addsub();
}
