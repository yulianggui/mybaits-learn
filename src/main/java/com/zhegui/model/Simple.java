package com.zhegui.model;

import com.zhegui.simple.SexEnum;

import java.util.Date;

/**
 * create by zhegui on 2018/10/20
 */
public class Simple {

    private Integer id;

    private String name;

    private String telephone;

    private Date createDate;

    private String createUser;

    private SexEnum sexEnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", createDate=" + createDate +
                ", createUser='" + createUser + '\'' +
                ", sexEnum = " + ((sexEnum == null)?"null":( sexEnum.getCode() + "," + sexEnum.getCodeStr())) +
                '}';
    }
}
