package com.test.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
@WebFilter(
        asyncSupported = true,
        value = "/*",//拦截的url格式
        filterName = "AuthenticationFilter",initParams = {
        @WebInitParam(name="user-db",value = "mydb"),
        @WebInitParam(name="db-password",value = "1234.asd")
})
public class AuthenticationFilter implements Filter {
    private Connection connection;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化connection
        filterConfig.getInitParameter("user-db");
        filterConfig.getInitParameter("db-password");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //change the request's headers
        request.setAttribute("charset","UTF-8");
        chain.doFilter(new MyRequestWrapper((HttpServletRequest) request),new MyResponseWrapper((HttpServletResponse) response));
    }

    @Override
    public void destroy() {
        
    }
}
