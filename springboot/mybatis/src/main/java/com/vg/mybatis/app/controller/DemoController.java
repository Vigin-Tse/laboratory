package com.vg.mybatis.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试用
 * @author: xieweij
 * @create: 2020-12-17 17:08
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/method")
    public String demoTest(){
        return "Hi";
    }
}
