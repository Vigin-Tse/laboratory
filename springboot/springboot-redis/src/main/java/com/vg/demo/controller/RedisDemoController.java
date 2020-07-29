package com.vg.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xieweij
 * @create 2020/4/17 11:18
 */
@RestController
@RequestMapping("/controller")
public class RedisDemoController {

    @GetMapping("/demo")
    public String demo() {
        return "hello world!";
    }
}
