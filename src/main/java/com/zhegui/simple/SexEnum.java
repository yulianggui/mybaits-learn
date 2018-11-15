package com.zhegui.simple;

/**
 *  模拟TypeHandler
 * create by zhegui on 2018/10/20
 */
public enum  SexEnum {

    MAN(1, "男"),

    WOMAN(2, "女")
    ;
    private int code;

    private String codeStr;

    private SexEnum(int code, String codeStr){
        this.code = code;
        this.codeStr = codeStr;
    }

    public int getCode(){
        return code;
    }

    public String getCodeStr(){
        return codeStr;
    }

    public static SexEnum getSexEnum(int code){
        for (SexEnum item:SexEnum.values()) {
            if (item.getCode() == code){
                return item;
            }
        }
        return null;
    }
}
