package com.test.servlet.api;

import javax.jws.soap.InitParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        //控制空间对象的属性,通过这些空间对象属性web组件中间可以传递和共享信息
        HttpSession session=req.getSession();
        String testSession= (String)session.getAttribute("test");
        System.out.println(testSession);

        PrintWriter writer=resp.getWriter();
        writer.write("hello servlet");
        writer.close();
        resp.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp){

    }
}
