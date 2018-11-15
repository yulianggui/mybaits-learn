package com.zhegui.simple;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模拟自定义类型转换
 *   没有添加注解，则需要手动指定
 * create by zhegui on 2018/10/20
 */
public class SexEnumTypeHandler implements TypeHandler<SexEnum> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        System.out.println("setParameter");
        if(sexEnum != null){
            preparedStatement.setInt(i, sexEnum.getCode());
        }else{
            preparedStatement.setInt(i, 0);
        }
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println("getResult by String");
        int code = resultSet.getInt(s);
        return SexEnum.getSexEnum(code);
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, int i) throws SQLException {
        System.out.println("getResult by int");
        int code = resultSet.getInt(i);
        return SexEnum.getSexEnum(code);
    }

    @Override
    public SexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        System.out.println("getResult by callableStatement");
        int code = callableStatement.getInt(i);
        return SexEnum.getSexEnum(code);
    }
}
