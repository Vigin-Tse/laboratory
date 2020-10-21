package com.vg.mybatis.plus.dept;

import com.vg.mybatis.app.dao.mapper.SysDepartmentMapper;
import com.vg.mybatis.app.dao.model.SysDepartmentModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description: 测试使用mybatis-plus
 * @author: xieweij
 * @create: 2020-10-12 13:47
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeptCRUDTest {

    @Resource
    public SysDepartmentMapper departmentMapper;

    @Test
    public void insert(){

        SysDepartmentModel dept = new SysDepartmentModel();
        dept.setDeptName("自动化科技公司3");
        this.departmentMapper.insert(dept);
    }

    @Test
    @Transactional
    public void updata(){
        SysDepartmentModel model = this.departmentMapper.selectById(9);

        model.setDeptName("update");
        this.departmentMapper.updateById(model);
    }

}
