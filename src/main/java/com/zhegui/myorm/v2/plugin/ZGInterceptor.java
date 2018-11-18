package com.zhegui.myorm.v2.plugin;

import org.apache.ibatis.plugin.Invocation;

/**
 * create by zhegui on 2018/11/18
 */
public interface ZGInterceptor {

    Object intercept(ZGInvocation invocation) throws Throwable;

    Object plugin(Object target);
}
