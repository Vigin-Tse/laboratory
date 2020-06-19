package com.vg.basic.spring.aop.service;

import com.vg.basic.spring.aop.model.UserModel;

public interface UserService {

    void addUser();

    UserModel addUserByModel(UserModel user);
}
