package com.zhegui.myorm.v2.executor;

import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.config.ZGConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by zhegui on 2018/11/17
 */
public class CachingExecutor implements IExecutor{

    private IExecutor delegate;

    private String LODAING = "LODAING";

    private ConcurrentHashMap<String, Object> localObject = new ConcurrentHashMap<>();

    public CachingExecutor(IExecutor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T selectOne(MapperRegister.MapperDate mapperDate, Object param) {
        T object = null;
        String key = mapperDate.getSql() + String.valueOf(param);
        if(localObject.get(key) != null){
            return (T)localObject.get(key);
        }
        localObject.put(key, LODAING);
        try{
            object = delegate.selectOne(mapperDate, param);
        }finally {
            localObject.remove(key);
        }
        localObject.put(key, object);
        return (T)object;
    }

    @Override
    public <T> List<T> selectList(MapperRegister.MapperDate mapperDate, Object param) {
        return null;
    }

    @Override
    public int update(String sql, Object param) {
        return -1;
    }

    @Override
    public int delete(String sql, Object param) {
        return -1;
    }

    @Override
    public int insert(String sql, Object param) {
        return -1;
    }
}
