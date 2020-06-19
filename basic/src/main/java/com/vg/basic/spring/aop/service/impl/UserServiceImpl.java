package com.vg.basic.spring.aop.service.impl;

import com.vg.basic.spring.aop.model.UserModel;
import com.vg.basic.spring.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void addUser() {
        System.out.println("Service-插入用户");
    }

    @Override
    public UserModel addUserByModel(UserModel user) {
        System.out.println("Service-插入用户对象：userId=" + user.getUserId() + "，userName=" + user.getUserName());
        return user;
    }
}
