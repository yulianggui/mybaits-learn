package com.zhegui.myorm.v2.bean;

/**
 * create by zhegui on 2018/11/17
 */
public class Test {

    private String id;

    private String userName;

    private String age;

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
