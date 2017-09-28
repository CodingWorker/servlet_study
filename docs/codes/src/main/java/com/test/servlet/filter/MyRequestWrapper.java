package com.test.servlet.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
public class MyRequestWrapper extends ServletRequestWrapper {
    public MyRequestWrapper(ServletRequest request) {
        super(request);
    }
}
