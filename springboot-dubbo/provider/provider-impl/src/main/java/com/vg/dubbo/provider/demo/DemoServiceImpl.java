package com.vg.dubbo.provider.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @Description
 * @Author xieweij
 * @create 2020/5/6 16:11
 */
@Service
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    public String sayHey(String name){
        logger.info("我是日志");
        if ("0".equals(name)){
            throw new RuntimeException("name is 0");
        }
        return "Hey," + name + "!";
    }
}
