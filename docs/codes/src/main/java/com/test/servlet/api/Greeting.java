package com.test.servlet.api;

import org.omg.PortableInterceptor.ServerRequestInfo;
import sun.misc.Request;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/28
 * Project: my-servlet
 */
@WebServlet(urlPatterns = {"/greeting"})
public class Greeting extends HttpServlet implements RequestDispatcher{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException{
          RequestDispatcher rd=req.getRequestDispatcher("/index");
          rd.forward(req,resp);

//        String redirectUrl="http://www.baidu.com";
//        resp.sendRedirect(redirectUrl);
    }

    private void helloGet(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        resp.setContentType("text/html");
        resp.setBufferSize(100);
        OutputStream outputStream=resp.getOutputStream();
        outputStream.write("greeting/hello called".getBytes());
        outputStream.close();
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {

    }
}
