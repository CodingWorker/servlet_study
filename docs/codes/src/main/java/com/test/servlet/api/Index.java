package com.test.servlet.api;

import javafx.scene.media.Media;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/27
 * Project: my-servlet
 */
@WebServlet(value = "/index",asyncSupported = true,initParams = {
        @WebInitParam(name = "a",value = "aa"),
        @WebInitParam(name = "b",value = "bb")
})
public class Index extends HttpServlet{
    @Override
    public void init() throws ServletException {
        Enumeration<String> initParamsEnum=getInitParameterNames();
        while(initParamsEnum.hasMoreElements()){
            System.out.println(initParamsEnum.nextElement());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        HttpSession session=req.getSession();
        String testSession= (String)session.getAttribute("test");
        System.out.println(testSession);

        resp.setContentType("text/html");//set before response commited
        resp.setBufferSize(2048);//设置缓冲区的大小，默认的会将响应的任何输出直接输出到client
        PrintWriter writer=resp.getWriter();//返回character data
        writer.write("hello servlet");
        writer.close();
        resp.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        resp.setContentType("application/json");
        resp.setBufferSize(2048);
        OutputStream outputStream=resp.getOutputStream();
        outputStream.write("hello servlet".getBytes());
        outputStream.close();

        resp.flushBuffer();
    }
}
