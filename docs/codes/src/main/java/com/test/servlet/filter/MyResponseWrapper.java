package com.test.servlet.filter;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
public class MyResponseWrapper extends ServletResponseWrapper {
    public MyResponseWrapper(ServletResponse response){
        super(response);
    }
}
