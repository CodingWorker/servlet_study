package com.test.servlet.filter;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
public class MyResponseWrapper extends HttpServletResponseWrapper {
    public MyResponseWrapper(HttpServletResponse response){
        super(response);
    }
}
