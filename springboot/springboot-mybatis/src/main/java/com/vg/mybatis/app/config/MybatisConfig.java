package com.vg.mybatis.app.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MybatisConfig {

    /**
     * 编程式设置mapperscanner 扫描Mapper接口
     * @return
     */
    @Bean
    public MapperScannerConfigurer MapperScannerConfigurer(){
        log.info("初始化MapperScanner");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.vg.**.mapper");
        return mapperScannerConfigurer;
    }
}
