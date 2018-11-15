package com.zhegui.mapper;

import com.zhegui.model.Simple;
import org.apache.ibatis.annotations.Select;

/**
 * create by zhegui on 2018/10/20
 */
public interface SimpleMapper {

    Simple selectSimpleById(int id);

    void insertSimple(Simple simple);

    @Select("select id,name,telephone,create_date AS createDate,create_user AS createUser from simple where id = #{id}")
    Simple selectSimplyByKey(int id);

    Simple selectByIdHandlerEnum(int id);
}
