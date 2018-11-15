package com.zhegui.myorm.v1.executor;

import com.zhegui.model.Simple;
import com.zhegui.simple.SexEnum;

import java.sql.*;

/**
 * create by zhegui on 2018/11/15
 */
public class ZGSimpleExecutorImpl implements IExecutor{
    @Override
    public <T> T query(String statement, String parameter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Simple simple = null;
        try {
            //将class的类文件加载到JVM
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.81.15:3306/mybatis_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "123456");
            String sql = String.format(statement, Integer.parseInt(parameter));
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                simple = new Simple();
                simple.setName(rs.getString("name"));
                simple.setCreateUser(rs.getString("create_user"));
                simple.setTelephone(rs.getString("telephone"));
                simple.setCreateDate(rs.getDate("create_date"));
                simple.setId(rs.getInt("id"));
                int sex = rs.getInt("sex");
                if(sex == 1){
                    simple.setSexEnum(SexEnum.MAN);
                }else if(sex == 2){
                    simple.setSexEnum(SexEnum.WOMAN);
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T)simple;
    }
}
