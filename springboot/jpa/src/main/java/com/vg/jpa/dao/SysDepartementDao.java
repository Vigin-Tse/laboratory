package com.vg.jpa.dao;

import com.vg.jpa.domain.model.SysDepartment;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/7/20 14:33
 */
public interface SysDepartementDao {

    /**
     * 根据id获取唯一记录
     * @return
     */
    SysDepartment getById();

    /**
     * 根据 example 获取符合条件的记录集合
     * @return
     */
    List<SysDepartment> getByExample();

    /**
     * jpa 的 findByXxxxx 自动映射功能
     * @return
     */
    List<SysDepartment> findBydeptName();

    /**
     * 新建/更新
     */
    void saveAndUpdata();

    /**
     * 删除（物理删除）
     */
    void delete();
}
