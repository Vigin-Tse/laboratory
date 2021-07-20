package com.vg.jpa.repository;

import com.vg.jpa.domain.model.SysDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysDepartmentRepository extends JpaRepository<SysDepartment, Integer> {

    // 自定义 findbyXXX 方法--- jap会自动把 findby 后面的条件生成sql语句进行查询， 比如这个方法jap会根据Deptname把传入的参数（deptName）当条件查询
    List<SysDepartment> findByDeptName(String deptName);
}
