package com.zhegui.myorm.v2.executor;

import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.config.ZGConfiguration;
import com.zhegui.myorm.v2.enums.SqlActionType;
import com.zhegui.myorm.v2.statement.StatementHandle;

import java.util.List;

/**
 * create by zhegui on 2018/11/17
 */
public class SimpleExecutor implements IExecutor{

    private ZGConfiguration configuration;

    public SimpleExecutor(ZGConfiguration configuration) {
        this.configuration = configuration;
    }

    public ZGConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T selectOne(MapperRegister.MapperDate mapperDate, Object param) {
        StatementHandle statementHandle = new StatementHandle(configuration);
        return statementHandle.query(mapperDate, param);
    }

    @Override
    public <T> List<T> selectList(MapperRegister.MapperDate mapperDate, Object param) {
        return null;
    }

    @Override
    public int update(String sql, Object param) {
        StatementHandle statementHandle = new StatementHandle(configuration);
        return statementHandle.alertOperate(sql, param, SqlActionType.UPDATE);
    }

    @Override
    public int delete(String sql, Object param) {
        StatementHandle statementHandle = new StatementHandle(configuration);
        return statementHandle.alertOperate(sql, param, SqlActionType.DELETE);
    }

    @Override
    public int insert(String sql, Object param) {
        StatementHandle statementHandle = new StatementHandle(configuration);
        return statementHandle.alertOperate(sql, param, SqlActionType.INSERT);
    }
}
