package com.vg.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.vg.mybatis.app.dao.mapper.BizAccountMapper;
import com.vg.mybatis.app.dao.model.BizAccountModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 测试使用注解的方式（不需要写xml文件）进行crud
 * @author: xieweij
 * @create: 2020-10-12 13:47
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudAnnotationTest {

    @Resource
    private BizAccountMapper accountMapper;

    @Test
    public void getAll(){
        List<BizAccountModel> accounts = this.accountMapper.getAll();
        System.out.println(JSON.toJSONString(accounts));
    }

    @Test
    public void getById(){
        Integer id = 1;
        BizAccountModel account = this.accountMapper.getById(id);
        System.out.println(JSON.toJSONString(account));
    }

    @Test
    public void findByExample(){
        Integer id = 1;
        BizAccountModel example = new BizAccountModel();
        example.setId(1);

        List<BizAccountModel> accounts = this.accountMapper.findByExample(example);
        System.out.println(JSON.toJSONString(accounts));
    }
}
