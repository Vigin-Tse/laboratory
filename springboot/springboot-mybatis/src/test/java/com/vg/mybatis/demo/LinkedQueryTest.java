package com.vg.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.vg.mybatis.app.dao.item.DepartmentUserQueryItem;
import com.vg.mybatis.app.dao.item.UserDepartmentQueryItem;
import com.vg.mybatis.app.dao.mapper.SysDepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 测试连表查询（一对一、一对多、多对多）
 * @author: xieweij
 * @create: 2020-10-12 13:47
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinkedQueryTest {

    @Resource
    private SysDepartmentMapper departmentMapper;

    /**
     * 一对一
     */
    @Test
    void oneToOneQuery(){
        UserDepartmentQueryItem item = this.departmentMapper.oneToOneQuery(5);
        System.out.println(JSON.toJSONString(item));
    }

    /**
     * 一对多
     */
    @Test
    void oneToManyQuery(){
        List<DepartmentUserQueryItem> items = this.departmentMapper.oneToManyQuery(2);
        System.out.println(JSON.toJSONString(items));
    }

    /**
     * 多对多
     */
    @Test
    void manyToManyQuery(){
        List<DepartmentUserQueryItem> items = this.departmentMapper.manyToManyQuery();
        System.out.println(JSON.toJSONString(items));
    }
}
