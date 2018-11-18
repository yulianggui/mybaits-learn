package com.zhegui.myorm.v2.result;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * create by zhegui on 2018/11/17
 */
public class ResultSetHandle {

    public <T> T query(ResultSet rs, Class<?> type){
        Object object = null;
        try {
            if (rs.next()) {
                object = createObject(type);
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    setFile(object, field, rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)object;
    }

    private Object createObject(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    private void setFile(Object object, Field field, ResultSet rs) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        String fileIdName = field.getName();
        Method setFileMethod = object.getClass().getMethod("set"
                + fileIdName.substring(0,1).toUpperCase()
                + fileIdName.substring(1), new Class<?>[]{field.getType()});
        setFileMethod.invoke(object, getResult(field, rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        //bean属性的名字必须要和数据库column的名字一样
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if(String.class == type){
            return rs.getString(field.getName());
        }
        return rs.getString(field.getName());
    }
}
