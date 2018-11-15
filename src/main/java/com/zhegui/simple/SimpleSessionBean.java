package com.zhegui.simple;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * create by zhegui on 2018/10/20
 */
public class SimpleSessionBean {

    private static SqlSessionFactory sqlSessionFactory = null;

    private SimpleSessionBean(){}

    static {
        String resource = "simple/mybatis-simple.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
