package com.zhegui.simple;

import com.zhegui.mapper.SimpleMapper;
import com.zhegui.model.Simple;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * create by zhegui on 2018/10/20
 */
public class TestSimple {

    public static void main(String[] args) {
        SqlSession session = SimpleSessionBean.getSession();
        SimpleMapper simpleMapper = session.getMapper(SimpleMapper.class);
        Simple simple = simpleMapper.selectSimpleById(2);
        System.out.println("---------------------");
        //测试mybatis一级缓存
        simple = simpleMapper.selectSimpleById(2);
        System.out.println("发现只是调用了一次查询，只打印一次SQL");
        System.out.println(simple);

        //注解的方式
        Simple simple1 = simpleMapper.selectSimplyByKey(2);
        System.out.println(simple1);
        /*Simple pojo = new Simple();
        pojo.setId(2);
        pojo.setName("insert into");
        pojo.setTelephone("10010");
        pojo.setCreateDate(new Date());
        pojo.setCreateUser("test simple");
        simpleMapper.insertSimple(pojo);
        session.commit();*/


    }
}
