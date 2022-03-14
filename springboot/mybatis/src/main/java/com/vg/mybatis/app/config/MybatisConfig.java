package com.vg.mybatis.app.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MybatisConfig {

    /**
     * 编程式设置mapperscanner 扫描Mapper接口（可以在mapper接口上使用@Mapper注解告知spring ioc自动注册Bean）
     * @return
     */
    @Bean
    public MapperScannerConfigurer MapperScannerConfigurer(){
        log.info("初始化MapperScanner");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.vg.**.mapper");
        return mapperScannerConfigurer;
    }

    /**
     * mybatis-plus 插件配置（3.4版本）
     * @return
     */
    @Bean
    public MybatisPlusInterceptor MybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //乐观锁
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 新的分页插件,一缓和二缓遵循mybatis的规则,
        // （（Mybatis Plus 3.4.0版本及其之后的版本）需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
///*        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(MybatisConfiguration configuration) {
//                configuration.setUseDeprecatedExecutor(false);
//            }
//        };*/
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }
}
