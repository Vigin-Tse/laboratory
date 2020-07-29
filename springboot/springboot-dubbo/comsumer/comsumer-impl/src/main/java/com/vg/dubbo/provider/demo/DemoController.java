package com.vg.dubbo.provider.demo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/6 16:40
 */
@RestController
public class DemoController {

    @Reference
    private DemoService demoService;

    @PostMapping("/hey")
    public String sayHello() throws RuntimeException {
        return demoService.sayHey("0");
    }
}