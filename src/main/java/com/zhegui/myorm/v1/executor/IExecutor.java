package com.zhegui.myorm.v1.executor;

/**
 * create by zhegui on 2018/11/15
 */
public interface IExecutor {

    public <T> T query(String stateMent, String parameter);
}
