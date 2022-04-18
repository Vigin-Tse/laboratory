package com.vg.share.configuration;

import com.vg.share.properties.ShareExamplePropertise;
import com.vg.shareexample.ShareDemo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ShareExamplePropertise.class)
@Configuration
public class ShareExampleConguration {

    @Bean
    public ShareDemo shareDemo(ShareExamplePropertise propertise){
        ShareDemo shareDemo = new ShareDemo();
        shareDemo.setName(propertise.getName());
        return shareDemo;
    }
}
