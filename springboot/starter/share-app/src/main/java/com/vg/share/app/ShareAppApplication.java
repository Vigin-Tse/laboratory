package com.vg.share.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.vg"}) //第一种扫描非默认包下bean方式，制定扫描路径
//第二种加载非默认包下组件方式 自动配置 扫描包中spring.factories文件（
// @SpringBootApplication -> @EnableAutoConfiguration -> @Import({AutoConfigurationImportSelector.class})）

@SpringBootApplication
public class ShareAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareAppApplication.class, args);
    }

}
