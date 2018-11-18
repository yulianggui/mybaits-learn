package com.zhegui.myorm.v2.plugin;

import org.apache.ibatis.plugin.Signature;

import java.lang.annotation.*;

/**
 * create by zhegui on 2018/11/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ZGIntercepts {
    ZGSignature[] value();
}
