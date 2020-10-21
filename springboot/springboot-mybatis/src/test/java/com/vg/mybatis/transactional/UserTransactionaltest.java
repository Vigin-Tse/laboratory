package com.vg.mybatis.transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vg.mybatis.app.dao.mapper.SysUserMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import com.vg.mybatis.app.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 测试使用mybatis-plus
 * @author: xieweij
 * @create: 2020-10-12 13:47
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTransactionaltest {
    @Autowired
    public SysUserService userService;

    @Test
    public void requiresNesTest(){
        userService.testAddUserTransactional();
    }

}
