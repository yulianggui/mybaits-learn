package com.zhegui.myorm.v2.bind;

import com.zhegui.myorm.v2.enums.SqlActionType;

import java.util.HashMap;
import java.util.Map;

/**
 * create by zhegui on 2018/11/17
 */
public class MapperRegister {

    private Map<String, MapperDate> mapperSql = new HashMap<String, MapperDate>();

    public class MapperDate<T>{

        private String sql;
        private String methodName;
        private SqlActionType sqlActionType;
        private Class<T> type;
        private Class<T> resultType;

        public MapperDate(String sql, String methodName, SqlActionType sqlActionType,Class<T> type, Class<T> resultType) {
            this.sql = sql;
            this.methodName = methodName;
            this.type = type;
            this.sqlActionType = sqlActionType;
            this.resultType = resultType;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }

        public SqlActionType getSqlActionType() {
            return sqlActionType;
        }

        public String getMethodName() {
            return methodName;
        }

        public Class<T> getResultType() {
            return resultType;
        }
    }

    public MapperDate get(String key){
        return mapperSql.get(key);
    }

    public void addMapperSql(String sql, String methodName, SqlActionType sqlActionType, Class<?> clazzType, Class<?> resultType){
        String key = clazzType.getName() + "." + methodName;
        if(mapperSql.get(key) != null){
            System.out.println("key already put !");
            return;
        }
        MapperDate mapperDate = new MapperDate(sql, methodName, sqlActionType, clazzType, resultType);
        mapperSql.put(key, mapperDate);
    }
}
