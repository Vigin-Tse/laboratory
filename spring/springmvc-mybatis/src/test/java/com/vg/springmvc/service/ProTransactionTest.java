package com.vg.springmvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)//junit整合spring的测试//立马开启了spring的注解
@ContextConfiguration(locations="classpath:applicationContext.xml")//加载核心配置文件，自动构建spring容器
public class ProTransactionTest {

    @Autowired
    private ProTransaction proTransaction;


    /**
     * 测试 编程式事务 demo
     */
    @Test
    public void testProTransaction(){
        proTransaction.inserUser();
    }

    /**
     * 测试 jdbc连接 事务回滚 demo
     */
    @Test
    public void testProJdbcConnection() throws SQLException {
        proTransaction.deleteUser();
    }
}
