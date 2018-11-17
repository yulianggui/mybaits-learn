package com.zhegui.myorm.v1.biand;

import com.zhegui.myorm.v1.configuration.ZGConfiguration;
import com.zhegui.myorm.v1.session.ZGSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create by zhegui on 2018/11/15
 */
public class ZGMapperProxy implements InvocationHandler{

    //在Invoke里面需要调用sqlSession的SelectOne方法,因此需要用到SqlSession
    private ZGSqlSession sqlSession;

    public ZGMapperProxy(ZGSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //如果是Object类的方法,执行ZGMapperProxy的方法
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this, args);
        }

        //如果是public的，并且，方法声明是一个接口（JDK1.8可以定义没有实现的方法）
        if((method.getModifiers() & 1033) == 1 && method.getDeclaringClass().isInterface()){
            //
            System.out.println("忽略！");
            return null;
        }

        //如果是Mapper的接口
        if(ZGConfiguration.TestMapperXml.namespace.equals(method.getDeclaringClass().getName())){
            String sql = ZGConfiguration.TestMapperXml.mapperSQL.get(method.getDeclaringClass().getName() + "." + method.getName());
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return null;
    }
}
