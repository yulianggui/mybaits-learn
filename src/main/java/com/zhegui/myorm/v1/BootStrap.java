package com.zhegui.myorm.v1;

import com.zhegui.model.Simple;
import com.zhegui.myorm.v1.configuration.ZGConfiguration;
import com.zhegui.myorm.v1.executor.ZGSimpleExecutorImpl;
import com.zhegui.myorm.v1.mapper.TestMapper;
import com.zhegui.myorm.v1.session.ZGSqlSession;

/**
 * create by zhegui on 2018/11/15
 */
public class BootStrap {
    public static void main(String[] args) {
        ZGSqlSession sqlSession = new ZGSqlSession(new ZGConfiguration(), new ZGSimpleExecutorImpl());
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        Simple simple = testMapper.selectSimpleById("5");
        System.out.println(simple);
    }
}
