package com.vg.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.vg.mybatis.app.dao.mapper.SysUserMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description: 测试使用传统的XML配置方式进行crud
 * @author: xieweij
 * @create: 2020-10-12 13:47
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudXmlTest {

    @Resource
    public SysUserMapper userMapper;

    @Test
    public void getUserById(){
        Integer userId = 1;
        SysUserModel user = this.userMapper.getById(1);
        System.out.println(JSON.toJSONString(user));
    }
}
