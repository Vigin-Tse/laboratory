package com.vg.mybatis.plus.user;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vg.mybatis.app.dao.mapper.SysUserMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import com.vg.mybatis.app.iservice.SysUserIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIServiceTest {

    @Resource
    public SysUserMapper sysUserMapper;

    @Autowired
    public SysUserIService sysUserIService;


    /**
     * iservice-save()测试-插入
     * 成功
     */
    @Test
    public void saveForNew(){
        SysUserModel u1 = new SysUserModel();
        u1.setUserName("Test service save");
        u1.setAge(25);
        u1.setDeptId(1);

        this.sysUserIService.save(u1);

    }

    /**
     * iservice-save()测试-更新
     *
     * 底层执行sql: INSERT INTO sys_user ( dept_id, user_name, age ) VALUES ( ?, ?, ? )
     * 更新失败：底层sql语句屏蔽id值，执行插入
     */
    @Test
    public void saveForId(){
        SysUserModel u1 = new SysUserModel();
        u1.setId(41);
        u1.setUserName("Test service save with id");
        u1.setAge(25);
        u1.setDeptId(1);

        this.sysUserIService.save(u1);

    }

    @Test
    public void select(){

        SysUserModel example = new SysUserModel();
        example.setAge(25);

//        List<SysUserModel> users = this.sysUserMapper.selectList(Wrappers.query(example));
        List<SysUserModel> users = this.sysUserMapper.selectList(Wrappers.lambdaQuery(example).eq(SysUserModel::getVersion, 4));
        System.out.println(JSON.toJSONString(users));
    }
}
