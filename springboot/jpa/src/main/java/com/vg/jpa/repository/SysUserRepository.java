package com.vg.jpa.repository;

import com.vg.jpa.domain.entity.SysUserEntity;
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
     * 测试原生sql返回自定义实体类结果---不能这样用
     *  You have an error in your SQL syntax;
     *  check the manual that corresponds to your MySQL server version for the right syntax to use near
     *  '.vg.jpa.domain.entity.SysUserEntity(u.id, u.user_name) from sys_user u' at line 1
     * @return
     */
    @Query(value = "select new com.vg.jpa.domain.entity.SysUserEntity(u.id, u.user_name) from sys_user u", nativeQuery = true)
    List<SysUserEntity> getAllUserWithEntity();

    /**
     * 测试原生sql返回数据库实体类结果
     * @return
     */
//    @Query(value = "select u.id, u.user_name from sys_user u", nativeQuery = true)//属性数量不对应，无法映射至结果实体类
    @Query(value = "select u.* from sys_user u", nativeQuery = true)
    List<SysUser> getAllUser();

    /**
     * 测试原生sql返回map对象
     * @return
     */
    @Query(value = "SELECT u.id, u.user_name, d.dept_name FROM sys_user u LEFT JOIN sys_department d ON u.dept_id = d.id",
            nativeQuery = true)
    List<Map<String, Object>> getAllUserDepat();

    /**
     * jpql返回实体类对象
     * @return
     */
    @Query(value = "SELECT new com.vg.jpa.domain.entity.UserDepartmentEntity(u.id, u.userName, d.deptName)  FROM SysUser u LEFT JOIN SysDepartment d ON u.deptId = d.id")
//    List<Optional<UserDepartmentEntity>> getAllUserDepatByJpql();
    List<UserDepartmentEntity> getAllUserDepatByJpql();

    /**
     * jpql返回实体类对象
     * @return
     */
    @Query(value = "SELECT new com.vg.jpa.domain.entity.UserDepartmentEntity(u.id, u.userName, d.deptName)  FROM SysUser u LEFT JOIN SysDepartment d ON u.deptId = d.id where u.id = :id")
//    Optional<UserDepartmentEntity> getUserDepatByJpql(@Param("id") Integer id);
    UserDepartmentEntity getUserDepatByJpql(@Param("id") Integer id);
}
