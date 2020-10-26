package com.vg.jpa.repository;

import com.vg.jpa.domain.entity.UserDepartmentEntity;
import com.vg.jpa.domain.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    List<SysUser> findByuserName(String userName);

    /**
     * 测试原生sql返回map对象
     * @return
     */
    @Query(value = "SELECT u.id, u.user_name, d.dept_name FROM SysUser u LEFT JOIN sys_department d ON u.dept_id = d.id",
            nativeQuery = true)
    List<Map<String, Object>> getAllUserDepat();

    /**
     * jpql返回实体类对象
     * @return
     */
    @Query(value = "SELECT new com.vg.jpa.domain.entity.UserDepartmentEntity(u.id, u.userName, d.deptName)  FROM SysUser u LEFT JOIN SysDepartment d ON u.deptId = d.id")
    List<Optional<UserDepartmentEntity>> getAllUserDepatByJpql();

    /**
     * jpql返回实体类对象
     * @return
     */
    @Query(value = "SELECT new com.vg.jpa.domain.entity.UserDepartmentEntity(u.id, u.userName, d.deptName)  FROM SysUser u LEFT JOIN SysDepartment d ON u.deptId = d.id where u.id = :id")
    Optional<UserDepartmentEntity> getUserDepatByJpql(@Param("id") Integer id);
}
