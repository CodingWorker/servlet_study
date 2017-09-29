package com.test.servlet_jiaocheng.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.*;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/29
 * Project: ServletDemo
 */
@WebFilter(urlPatterns = {"/*"},
initParams = {
        @WebInitParam(name="logFiltPath",value="F:/tmp/log.txt")
})
public class LogFilter implements Filter {
    private static String logFiltPath;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logFiltPath=filterConfig.getInitParameter("logFiltPath");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FileWriter writer = null;

        try {
            writer = new FileWriter(logFiltPath,true);
            String user=request.getParameter("user");
            if(null==user){
                user="unknown user";
            }

            writer.append(user+" login on ...\r\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            writer.close();
        }

        chain.doFilter(request,response);//不添加这句的话对request的处理会被截取
    }

    @Override
    public void destroy() {

    }
}
