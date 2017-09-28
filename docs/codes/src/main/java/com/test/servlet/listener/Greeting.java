package com.test.servlet.listener;

import com.mysql.jdbc.ReplicationMySQLConnection;
import com.oracle.jrockit.jfr.ValueDefinition;

import javax.jws.WebService;
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
@WebServlet("/greeting")
public class Greeting extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        String reqUrl= req.getRequestURI();
        if(reqUrl.endsWith("greeting/hello")){
            helloGet(req,resp);
        }
    }

    private void helloGet(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        resp.setContentType("text/html");
        resp.setBufferSize(100);
        OutputStream outputStream=resp.getOutputStream();
        outputStream.write("greeting/hello called".getBytes());
        outputStream.close();
    }
}
