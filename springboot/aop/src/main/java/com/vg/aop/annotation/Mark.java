package com.vg.aop.annotation;

import java.lang.annotation.*;

/**
 * @author: xieweij
 * @time: 2021/9/2 9:25
 */
//@Inherited //允许子类继承父类中的注解
@Documented //将注解包含在Javadoc中
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mark {
}
