package com.vg.springmvc.controller;

import com.vg.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vg.springmvc.model.SysUserModel;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public SysUserModel getCustomerById(@RequestBody SysUserModel request) {
        return this.userService.getUserById(request.getId());
    }

    @RequestMapping("/add")
    public void addUser(@RequestBody SysUserModel user){
        this.userService.addUser(user);
    }
    
    @RequestMapping("/update")
    public void updateUser(@RequestBody SysUserModel user){
    	this.userService.updateUser(user);
    }
    
    @RequestMapping("/addsub")
    public void updateUser(){
    	this.userService.addsub();
    }
    

	@RequestMapping("/param/{name}")
    public String getParam(@PathVariable String name){
        return name;
    }
}
