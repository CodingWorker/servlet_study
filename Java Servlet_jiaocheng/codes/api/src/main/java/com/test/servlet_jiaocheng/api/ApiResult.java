package com.test.servlet_jiaocheng.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class ApiResult {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String msg;

    @JsonProperty("data")
    private Object data;

    public String toJsonString(){
        ObjectMapper om=new ObjectMapper();
        String resultStr=null;
        try{
            resultStr= om.writeValueAsString(this);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return resultStr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
