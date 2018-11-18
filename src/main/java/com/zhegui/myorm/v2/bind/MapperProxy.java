package com.zhegui.myorm.v2.bind;

import com.zhegui.myorm.v2.config.ZGConfiguration;
import com.zhegui.myorm.v2.session.ZGSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create by zhegui on 2018/11/17
 */
public class MapperProxy implements InvocationHandler{

    private ZGSqlSession sqlSession;

    private Class<?> interfaces;

    public MapperProxy(ZGSqlSession sqlSession, Class<?> interfaces) {
        this.sqlSession = sqlSession;
        this.interfaces = interfaces;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key = method.getDeclaringClass().getName() + "." + method.getName();
        MapperRegister.MapperDate mapperDate = sqlSession.getConfiguration().getMapperRegister().get(key);
        if(mapperDate != null){
            switch (mapperDate.getSqlActionType()){
                case SELECT:
                    return sqlSession.selectOne(mapperDate, args[0]);
                case UPDATE:
                    return sqlSession.update(mapperDate, args[0]);
                case DELETE:
                    return sqlSession.delete(mapperDate, args[0]);
                case INSERT:
                    return sqlSession.insert(mapperDate, args[0]);
                 default:
                     break;
            }
        }else if(isDefaultMethod(method)){
            System.out.println("执行了接口的方法！");
            return null;
        }
        return method.invoke(proxy, args);
    }

    private boolean isDefaultMethod(Method method) {
        return (method.getModifiers() & 1033) == 1 && method.getDeclaringClass().isInterface();
    }
}
