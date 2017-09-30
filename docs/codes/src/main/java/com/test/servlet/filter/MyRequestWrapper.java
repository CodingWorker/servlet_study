package com.test.servlet.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {
    public MyRequestWrapper(HttpServletRequest request) {
        super(request);
    }
}
