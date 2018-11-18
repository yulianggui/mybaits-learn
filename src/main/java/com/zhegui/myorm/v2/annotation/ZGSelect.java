package com.zhegui.myorm.v2.annotation;

import java.lang.annotation.*;

/**
 * create by zhegui on 2018/11/17
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface ZGSelect {

    String sql() default "";
}
