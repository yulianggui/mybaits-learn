package com.zhegui.myorm.v2.executor;

import com.zhegui.myorm.v2.bind.MapperRegister;

import java.util.List;

/**
 * create by zhegui on 2018/11/17
 */
public interface IExecutor {

    <T> T selectOne(MapperRegister.MapperDate mapperDate, Object param);

    <T> List<T> selectList(MapperRegister.MapperDate mapperDate, Object param);

    int update(String sql, Object param);

    int delete(String sql, Object param);

    int insert(String sql, Object param);
}
