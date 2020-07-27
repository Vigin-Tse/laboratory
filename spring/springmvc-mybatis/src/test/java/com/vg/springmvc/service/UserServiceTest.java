package com.vg.springmvc.service;


import com.alibaba.fastjson.JSONObject;
import com.vg.springmvc.model.SysUserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//junit整合spring的测试//立马开启了spring的注解
@ContextConfiguration(locations="classpath:applicationContext.xml")//加载核心配置文件，自动构建spring容器
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetOneUser(){
        SysUserModel user = this.userService.getUserById(1);
        System.out.println(JSONObject.toJSON(user));
    }

}
