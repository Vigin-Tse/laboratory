package com.vg.mybatis.app.service.impl;

import com.vg.mybatis.app.dao.mapper.SysDepartmentMapper;
import com.vg.mybatis.app.dao.model.SysDepartmentModel;
import com.vg.mybatis.app.service.SysDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description: 部门
 * @author: xieweij
 * @create: 2020-10-21 11:56
 **/
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Resource
    private SysDepartmentMapper departmentMapper;

    /**
     * 重新创建一个新的事务，如果当前存在事务，暂停当前的事务
     * @param deptName
     */
//    @Transactional
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void testAddDeptTransactional() {
        SysDepartmentModel dept = new SysDepartmentModel();
        dept.setDeptName("事务测试");

        this.departmentMapper.insert(dept);
        int i = 1/0;
    }
}
