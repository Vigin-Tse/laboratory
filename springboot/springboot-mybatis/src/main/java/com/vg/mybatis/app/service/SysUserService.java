package com.vg.mybatis.app.service;

import com.vg.mybatis.app.dao.model.SysUserModel;

import java.util.List;

/**
 * @description: 用户
 * @author: xieweij
 * @create: 2020-10-20 09:42
 **/
public interface SysUserService {

    void addUser(SysUserModel user);

    void addUsers(List<SysUserModel> userModels);

    void deleteUser(Integer userId);

    List<SysUserModel> queryUsers(List<Integer> userIds);

    void testAddUserTransactional();
}
