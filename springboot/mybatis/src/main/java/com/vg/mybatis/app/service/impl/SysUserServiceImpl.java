package com.vg.mybatis.app.service.impl;

import com.vg.mybatis.app.dao.mapper.SysUserMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import com.vg.mybatis.app.service.SysDepartmentService;
import com.vg.mybatis.app.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户
 * @author: xieweij
 * @create: 2020-10-20 09:43
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Autowired
    private SysDepartmentService departmentService;


    @Override
    public void addUser(SysUserModel user) {
        this.userMapper.insert(user);
    }

    @Override
    public void addUsers(List<SysUserModel> userModels) {
        for(SysUserModel u :userModels){
            this.addUser(u);
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        this.userMapper.deleteById(userId);
    }

    @Override
    public List<SysUserModel> queryUsers(List<Integer> userIds) {
        return this.userMapper.selectBatchIds(userIds);
    }

    @Transactional
    @Override
    public void testAddUserTransactional() {
        SysUserModel user = new SysUserModel();
        user.setUserName("测试事务");
        this.userMapper.insert(user);

        try {
            this.departmentService.testAddDeptTransactional();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
