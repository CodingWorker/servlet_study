package com.test.servlet_jiaocheng.contants;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public enum  MIMEType {
    APPLICATION_JSON("application/json");

    //json
    private String typeValue;
    MIMEType(String typeValue){
        this.typeValue=typeValue;
    }

    public String typeValue(){
        return this.typeValue;
    }
}
