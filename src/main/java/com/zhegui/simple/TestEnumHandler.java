package com.zhegui.simple;

import com.zhegui.mapper.SimpleMapper;
import com.zhegui.model.Simple;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * create by zhegui on 2018/10/20
 */
public class TestEnumHandler {

    public static void main(String[] args) {
        SqlSession session = SimpleSessionBean.getSession();
        SimpleMapper simpleMapper = session.getMapper(SimpleMapper.class);
        Simple simple = simpleMapper.selectByIdHandlerEnum(-1);
        System.out.println(simple);

        Simple simple1 = new Simple();
        simple1.setId(5);
        simple1.setName("enumTest");
        simple1.setTelephone("10010");
        simple1.setCreateDate(new Date());
        simple1.setCreateUser("enumssss");
        simple1.setSexEnum(SexEnum.WOMAN);
        try {
            simpleMapper.insertSimple(simple1);
            session.commit();
        }finally {
            session.close();
        }
        Simple simple555 = simpleMapper.selectByIdHandlerEnum(5);
        System.out.println(simple555);
    }
}
