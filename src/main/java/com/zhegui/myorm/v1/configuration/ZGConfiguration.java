package com.zhegui.myorm.v1.configuration;

import com.zhegui.myorm.v1.biand.ZGMapperProxy;
import com.zhegui.myorm.v1.session.ZGSqlSession;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * create by zhegui on 2018/11/15
 */
public class ZGConfiguration {

    /**
     * 从configuration中返回Mapper
     * @param type 类型
     * @param <T>  返回T
     * @return  泛型方式
     */
    public <T> T getMapper(Class<?> type, ZGSqlSession sqlSession){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type},
                new ZGMapperProxy(sqlSession));
    }

    public static class TestMapperXml{
        public static final String namespace = "com.zhegui.myorm.v1.mapper.TestMapper";

        public static final Map<String, String> mapperSQL = new HashMap<String, String>();

        static {
            mapperSQL.put(namespace + "." + "selectSimpleById",
                    "select * from simple where id =%d ");
        }
    }
}
