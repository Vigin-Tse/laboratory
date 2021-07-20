package com.vg.jpa.dao.impl;

import com.vg.jpa.dao.SysDepartementDao;
import com.vg.jpa.domain.model.SysDepartment;
import com.vg.jpa.domain.model.SysUser;
import com.vg.jpa.repository.SysDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: xieweij
 * @time: 2021/7/20 14:34
 */
@Service
public class SysDepartementDaoImpl implements SysDepartementDao {

    @Autowired
    private SysDepartmentRepository departmentRepository;

    @Override
    public SysDepartment getById() {
        int id = 1;

//        this.departmentRepository.getOne(id) 别使用jpa提供的这个方法（getOne），这个方法有bug

        //使用jpa提供的方法（findById）查询提供api
        Optional<SysDepartment> department = this.departmentRepository.findById(id);
        SysDepartment model = department.orElse(null);
        return model;
    }

    @Override
    public List<SysDepartment> getByExample() {

        //example : 条件
        SysDepartment example = new SysDepartment();
        example.setId(1);
        example.setDeptName("xxxx");

        //根据example设置进去的条件查询符合条件的姐结果集
        Example<SysDepartment> e = Example.of(example);
        List<SysDepartment> result = this.departmentRepository.findAll(e);

        return result;
    }

    @Override
    public List<SysDepartment> findBydeptName() {
        //使用jpa自定义方法查询
        return this.departmentRepository.findByDeptName("xxxx");
    }

    @Override
    public void saveAndUpdata() {
        SysDepartment department = new SysDepartment();
        department.setId(1); //d调用save()方法时，新增：id不设置任何值 ； 更新：id有设置就是更新
        department.setDeptName("yyy");

        //调用jpa提供的保存方法save
        this.departmentRepository.save(department);
    }

    @Override
    public void delete() {
        //调用jpa提供的根据id删除方法
        this.departmentRepository.deleteById(1);
    }
}
