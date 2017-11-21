package com.test.servlet_jiaocheng;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/29
 * Project: ServletDemo
 */
@WebServlet(name="MyFirstServlet",urlPatterns ={"/first"},initParams = {//这里的注解不是没有意义的，是用来代替web.xml的，
        //name对应的是web.xml中注册web.xml，urlPatterns 对应的是web.xml中注册的servlet映射关系
        @WebInitParam(name="a",value = "aa"),
        @WebInitParam(name="b",value="bb")
})
public class FirstServlet extends HttpServlet{
    @Override
    public void init(ServletConfig config){
        System.out.println(config.getInitParameter("a"));
        System.out.println(config.getInitParameter("b"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setBufferSize(1024);
        PrintWriter writer=null;
        try {
            writer = resp.getWriter();
            writer.write("<html><head><title>First Servlet</title></head><body>");
            writer.write("<h1>hello, this is my first servlet!</h1>");
            writer.write("</body>");
            writer.write("</html>");
            writer.close();
            resp.flushBuffer();
        }catch(IOException ex){
            ex.printStackTrace();
        }finally {
            if(null!=writer){
                try{
                    writer.close();//像这种涉及流的对象，在操作时最好的办法就是
                    // 使用try-catch-finally接口以确保在最后关闭流，数据库的连接也是一样
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setBufferSize(1024);
        OutputStream outputStream=resp.getOutputStream();
        outputStream.write("{\"code\":0,\"msg\":\"greeting-msg(hello, this is my first servlet!)\"}".getBytes(Charset.forName("UTF-8")));
        resp.flushBuffer();
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
