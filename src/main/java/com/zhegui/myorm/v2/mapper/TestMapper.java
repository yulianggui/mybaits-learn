package com.zhegui.myorm.v2.mapper;

import com.zhegui.myorm.v2.annotation.ZGDelete;
import com.zhegui.myorm.v2.annotation.ZGInsert;
import com.zhegui.myorm.v2.annotation.ZGSelect;
import com.zhegui.myorm.v2.annotation.ZGUpdate;
import com.zhegui.myorm.v2.bean.Test;

/**
 * create by zhegui on 2018/11/17
 */
public interface TestMapper {

    @ZGSelect(sql = "select * from test where id = '%s'")
    Test selectById(String id);

    @ZGDelete(sql = "delete from test where id = '%s';")
    int deleteById(String id);

    @ZGUpdate(sql = "update test set age ='20' where id = '%s'")
    int updateById(String id);

    @ZGInsert(sql = "insert into test(id,userName,age) values('%s','testInsert','22')")
    int insertOne(String id);

    default void fun(){   //这是一个普通方法
        System.out.println("很神奇的一个方法。。。。。");
    }
}
