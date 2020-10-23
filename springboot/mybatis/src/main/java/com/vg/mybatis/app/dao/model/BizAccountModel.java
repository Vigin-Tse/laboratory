package com.vg.mybatis.app.dao.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BizAccountModel {

    private Integer id;

    private Integer userId;

    private Double accountBalance;

    private Integer version;
}
