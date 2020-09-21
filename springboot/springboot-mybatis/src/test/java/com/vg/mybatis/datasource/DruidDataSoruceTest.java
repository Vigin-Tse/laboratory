package com.vg.mybatis.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DruidDataSoruceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSourceMessage(){
        System.out.println(dataSource.getClass().getSimpleName());
    }
}
