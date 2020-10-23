package com.vg.mybatis.plus.user;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vg.mybatis.app.dao.mapper.SysUserMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import com.vg.mybatis.app.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 测试使用mybatis-plus
 * @author: xieweij
 * @create: 2020-10-12 13:47
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCRUDTest {

    @Resource
    public SysUserMapper userMapper;

    @Autowired
    public SysUserService userService;

    /**
     * 查询
     * eq	等于=
     * ne	不等与<>
     * gt	大于>
     * ge	大于等于>=
     * lt	小于<
     * le	小于等于<=
     * like	模糊查询 LIKE
     * notLike	模糊查询 NOT LIKE
     * in	IN 查询
     * notIn	NOT IN 查询
     * isNull	NULL 值查询
     * isNotNull	IS NOT NULL
     */
    @Test
    public void selectAll(){
//        QueryWrapper<SysUserModel> userQuery = new QueryWrapper<>();
//        userQuery.eq("dept_id", 2)
//        .eq("age", 23);

        //引入lambda
        QueryWrapper<SysUserModel> userQuery = new QueryWrapper<>();
        userQuery.lambda().eq(SysUserModel::getDeptId, 2)
                .eq(SysUserModel::getAge, 23);

        List<SysUserModel> users = this.userMapper.selectList(userQuery);
        System.out.println(JSON.toJSONString(users));
    }


    /**
     * 新增
     */
    @Test
    public void insert(){
        SysUserModel u1 = new SysUserModel();
        u1.setUserName("u1");
        u1.setAge(25);
        u1.setDeptId(1);

        this.userMapper.insert(u1);
    }

    /**
     * 更新
     */
    @Test
    public void udpata(){
        SysUserModel user = this.userMapper.selectById(3);
        System.out.println(JSON.toJSONString(user));

        user.setUserName("version add 1");
        this.userMapper.updateById(user);
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        this.userMapper.deleteById(34);
    }

    /**
     * 乐观锁测试, 乐观锁 保存时必须携带 version否则无效
     *
     * 结果 线程二修改成功, 线程一 修改失败
     */
    @Test
    public void OptimisticLocker() {

        // 线程一
        SysUserModel user1 = userMapper.selectById(3);
        // 获取 version
        user1.setUserName("我是线程一修改的name");

        // 线程二
        SysUserModel user2 = userMapper.selectById(3);
        // 获取 version
        user2.setUserName("我是线程二修改的name");

        userMapper.updateById(user2);

        userMapper.updateById(user1);

    }


    /**
     * 分页
     */
    @Test
    public void selectPage(){
        Page<SysUserModel> page = new Page<>(2,4);
        this.userMapper.selectPage(page, null);

        List<SysUserModel> users = page.getRecords();
        System.out.println(JSON.toJSONString(users));
        System.out.println("当前页码：" + page.getCurrent());
        System.out.println("总页数：" + page.getPages());
        System.out.println("记录总条数：" + page.getTotal());
    }
}
