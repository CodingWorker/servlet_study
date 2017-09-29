package com.test.servlet.filter;


import javax.naming.Name;
import javax.print.attribute.standard.MediaSize;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
@WebFilter(value = "/*",
initParams = {
        @WebInitParam(name="a",value = "aa"),
        @WebInitParam(name="b",value = "bb")
})
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getInitParameter("a"));
        System.out.println(filterConfig.getInitParameter("b"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //log for some reason
        chain.doFilter(new MyRequestWrapper((HttpServletRequest) request),new MyResponseWrapper((HttpServletResponse) response));
    }

    @Override
    public void destroy() {

    }
}
