package com.zhegui.myorm.v2.session;

import com.zhegui.myorm.v2.bind.MapperProxy;
import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.config.ZGConfiguration;
import com.zhegui.myorm.v2.executor.IExecutor;

import java.lang.reflect.Proxy;

/**
 * create by zhegui on 2018/11/17
 */
public class ZGSqlSession {

    private ZGConfiguration configuration;

    private IExecutor executor;

    public ZGSqlSession(ZGConfiguration configuration, IExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<?> type){
        return (T)Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type},
                new MapperProxy(this, type));
    }

    public ZGConfiguration getConfiguration() {
        return configuration;
    }

    public <T> T selectOne(MapperRegister.MapperDate mapperDate, Object param){
        //测试My plugin 插件
        executor = (IExecutor) configuration.getInterceptorChain().pluginAll(executor);
        return executor.selectOne(mapperDate, param);
    }

    public int update(MapperRegister.MapperDate mapperDate, Object param){
        return executor.update(mapperDate.getSql(), param);
    }

    public int insert(MapperRegister.MapperDate mapperDate, Object param){
        return executor.insert(mapperDate.getSql(), param);
    }

    public int delete(MapperRegister.MapperDate mapperDate, Object param){
        return executor.delete(mapperDate.getSql(), param);
    }
}
