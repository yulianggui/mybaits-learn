package com.zhegui.myorm.v2;

import com.zhegui.myorm.v2.bean.Test;
import com.zhegui.myorm.v2.config.ZGConfiguration;
import com.zhegui.myorm.v2.executor.SimpleExecutor;
import com.zhegui.myorm.v2.mapper.TestMapper;
import com.zhegui.myorm.v2.plugin.TestZGPlugin;
import com.zhegui.myorm.v2.plugin.TwoZGPlugin;
import com.zhegui.myorm.v2.session.ZGSqlSession;

import java.io.UnsupportedEncodingException;

/**
 * create by zhegui on 2018/11/17
 */

public class BootStrap {
    public static void main(String[] args) {
        try {
            ZGConfiguration configuration = new ZGConfiguration("com.zhegui.myorm.v2.mapper");
            configuration.getInterceptorChain().addInterceptor(new TestZGPlugin());
            configuration.getInterceptorChain().addInterceptor(new TwoZGPlugin());
            configuration.build();
            ZGSqlSession sqlSession = new ZGSqlSession(configuration, new SimpleExecutor(configuration));
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            Test test = testMapper.selectById("1");
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main2(String[] args) {
        try {
            ZGConfiguration configuration = new ZGConfiguration("com.zhegui.myorm.v2.mapper");
            configuration.build();
            ZGSqlSession sqlSession = new ZGSqlSession(configuration, new SimpleExecutor(configuration));
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            int result = testMapper.updateById("1");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main3(String[] args) {
        try {
            ZGConfiguration configuration = new ZGConfiguration("com.zhegui.myorm.v2.mapper");
            configuration.build();
            ZGSqlSession sqlSession = new ZGSqlSession(configuration, new SimpleExecutor(configuration));
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            int flag = testMapper.insertOne("3");
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main4(String[] args) {
        try {
            ZGConfiguration configuration = new ZGConfiguration("com.zhegui.myorm.v2.mapper");
            configuration.build();
            ZGSqlSession sqlSession = new ZGSqlSession(configuration, new SimpleExecutor(configuration));
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            int flag = testMapper.deleteById("3");
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
