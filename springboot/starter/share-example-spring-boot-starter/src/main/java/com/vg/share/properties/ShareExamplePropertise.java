package com.vg.share.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component //在配置文件使用@EnableConfigurationProperties做IOC依赖注入可省略此（@Component）注解
@ConfigurationProperties(prefix = "share.example")
public class ShareExamplePropertise {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
