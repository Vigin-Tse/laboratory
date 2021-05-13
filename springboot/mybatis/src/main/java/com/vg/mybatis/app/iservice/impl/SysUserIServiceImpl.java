package com.vg.mybatis.app.iservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vg.mybatis.app.dao.mapper.SysUserMapper;
import com.vg.mybatis.app.dao.model.SysUserModel;
import com.vg.mybatis.app.iservice.SysUserIService;
import org.springframework.stereotype.Service;

@Service
public class SysUserIServiceImpl extends ServiceImpl<SysUserMapper, SysUserModel> implements SysUserIService {
}
