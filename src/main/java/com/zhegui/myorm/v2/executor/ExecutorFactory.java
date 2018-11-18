package com.zhegui.myorm.v2.executor;

import com.zhegui.myorm.v2.config.ZGConfiguration;

/**
 * create by zhegui on 2018/11/17
 */
public final class ExecutorFactory {

    public static IExecutor getDefault(ZGConfiguration configuration){
        return new SimpleExecutor(configuration);
    }

    public static IExecutor getExecutor(String key, ZGConfiguration configuration){
        if("simple".equals(key)){
            return getDefault(configuration);
        }else if("caching".equals(key)){
            return new CachingExecutor(new SimpleExecutor(configuration));
        }else{
            throw new IllegalArgumentException("illegal key!");
        }

    }
}
