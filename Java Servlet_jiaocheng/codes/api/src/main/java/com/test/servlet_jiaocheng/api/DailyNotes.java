package com.test.servlet_jiaocheng.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
@WebServlet(name="dailynotes",urlPatterns = {"/dailynotes"})
public class DailyNotes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream out=resp.getOutputStream();
        PrintWriter pw=new PrintWriter(out);
        try{
            pw.write("daily notes");
            pw.flush();
        }finally {
            out.close();
            pw.close();
        }
    }
}
