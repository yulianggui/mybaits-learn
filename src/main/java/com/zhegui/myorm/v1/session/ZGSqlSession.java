package com.zhegui.myorm.v1.session;

import com.zhegui.myorm.v1.configuration.ZGConfiguration;
import com.zhegui.myorm.v1.executor.ZGSimpleExecutorImpl;

/**
 * create by zhegui on 2018/11/15
 */
public class ZGSqlSession {

    /**
     * 持有configuration
     */
    private ZGConfiguration configuration;

    /**
     * 持有executor
     */
    private ZGSimpleExecutorImpl executor;

    public ZGSqlSession(ZGConfiguration configuration, ZGSimpleExecutorImpl executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 模仿mybatis
     * @param type 类型
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> type){
        return configuration.getMapper(type, this);
    }

    /**
     * 查询一条语句
     * @param stateMent sql语句
     * @param parameter 参数
     * @param <T>
     * @return
     */
    public <T> T selectOne(String stateMent, String parameter){
        return executor.query(stateMent, parameter);
    }
}
