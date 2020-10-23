package com.vg.dubbo.provider.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/6 16:54
 */
@Service
public class DemoSonServiceImpl {

    private DemoService demoService;

    @Autowired
    private void demoServiceSetter(DemoService demoService) {
        this.demoService = demoService;
//        System.out.println(demoService.sayHey("son call father"));
    }

    public void hey() {
        System.out.println("demo's son");
    }
}
