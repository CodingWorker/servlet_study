package com.test.servlet_jiaocheng.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class ApiResultTest {
    @Test
    public void testToString(){
        ApiResult apiResult=new ApiResult();
        apiResult.setCode(0);
        apiResult.setMsg("ok");
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");

        apiResult.setData(list);

        System.out.println(apiResult.toJsonString());
    }
}
