package com.vg.basic.spring.beanlifecycle.bean.initialzing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/12 17:03
 */
@Component
public class InitializingBeanSon implements InitializingBean {

    /**
     * bean初始化时执行
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean.afterPropertiesSet()");
    }
}
