package com.zhegui.myorm.v2.plugin;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by zhegui on 2018/11/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface ZGSignature {

    Class<?> type();

    String method();

    Class<?>[] args();

}
