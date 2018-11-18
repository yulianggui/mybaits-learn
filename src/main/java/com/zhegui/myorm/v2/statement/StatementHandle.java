package com.zhegui.myorm.v2.statement;

import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.config.ZGConfiguration;
import com.zhegui.myorm.v2.enums.SqlActionType;
import com.zhegui.myorm.v2.result.ResultSetHandle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * create by zhegui on 2018/11/17
 */
public class StatementHandle {

    private final ResultSetHandle resultSetHandle;

    private ZGConfiguration configuration;

    public StatementHandle(ZGConfiguration configuration) {
        this.configuration = configuration;
        this.resultSetHandle = new ResultSetHandle();
    }

    public <T> T query(MapperRegister.MapperDate mapperDate, Object param){
        Connection connection = getConnection();
        if(connection == null){
            throw new RuntimeException("get connection error!");
        }

        try {
            String sql = mapperDate.getSql();
            sql = String.format(sql, String.valueOf(param));
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeQuery();
            return resultSetHandle.query(pstmt.getResultSet(), mapperDate.getResultType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int alertOperate(String sql, Object param, SqlActionType sqlActionType){
        System.out.println("sqlActionType :" + sqlActionType.name());

        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        if(connection == null){
            throw new RuntimeException("get connection error!");
        }
        try {
            connection.setAutoCommit(false);
            sql = String.format(sql, String.valueOf(param));
            pstmt = connection.prepareStatement(sql);
            int num = pstmt.executeUpdate();
            connection.commit();
            return num;
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    private Connection getConnection(){
       //将class的类文件加载到JVM
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://192.168.81.15:3306/mybatis_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "123456");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
