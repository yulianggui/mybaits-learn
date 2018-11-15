package com.zhegui.simple;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * 学习下mybatis的plugin功能
 *    拦截StatementHandler 下的prepare方法
 * create by zhegui on 2018/10/20
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class SQLLoggerInterceptor implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        System.out.println("自定义拦截器： sql = " + sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("someProperty");
        System.out.println("配置的数据库方言为： dialect =" + dialect);
    }
}
