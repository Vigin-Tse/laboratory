package com.vg.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/6 16:11
 */
@SpringBootApplication
@EnableDubbo
public class ComsumerRunner {

    public static void main(String[] args) {
        SpringApplication.run(ComsumerRunner.class, args);
    }
}
