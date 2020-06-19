package com.vg.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vg.springmvc.mapper.SysUserMapper;
import com.vg.springmvc.model.SysUserModel;
import com.vg.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUserModel getUserById(Integer id) {
        return this.userMapper.getById(id);
    }

    @Override
    public void addUser(SysUserModel user) {
        this.userMapper.insertUser(user);
    }
    
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void addsub() {
		
		this.ageAddOne(1);
		SysUserModel vigin = this.getUserById(1);
		System.out.println("after add: name=" + vigin.getName() + ",age=" + vigin.getAge());
		
		this.agesubOne(2);
		SysUserModel tony = this.getUserById(2);
		System.out.println("after sub: name=" + tony.getName() + ",age=" + tony.getAge());
		
		System.out.println("out");
	}

	@Override
	public void updateUser(SysUserModel user) {
		this.userMapper.updateUser(user);
	}
	

    private void ageAddOne(Integer id) {
    	SysUserModel vigin = this.getUserById(1);
    	
    	System.out.println("before add: name=" + vigin.getName() + ",age=" + vigin.getAge());
    	
    	vigin.setAge(vigin.getAge() + 1);
    	
    	this.userMapper.updateUser(vigin);
    	
    }

    private void agesubOne(Integer id) {
    	SysUserModel tony = this.getUserById(2);
    	
    	System.out.println("before sub: name=" + tony.getName() + ",age=" + tony.getAge());
    	
    	tony.setAge(tony.getAge() - 1);
    	
    	this.updateUser(tony);
    	
    }

}
