package com.vg.mybatis.app.demo.dao.mapper;

import com.vg.mybatis.app.demo.dao.model.BizAccountModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BizAccountMapper {

    @Select("select * from biz_account")
    List<BizAccountModel> getAll();

    @Select("select * from biz_account where id = #{id}")
    BizAccountModel getById(Integer id);

    @Select("select * from biz_account where id = #{ex.id}")
    List<BizAccountModel> findByExample(@Param("ex") BizAccountModel example);
}
