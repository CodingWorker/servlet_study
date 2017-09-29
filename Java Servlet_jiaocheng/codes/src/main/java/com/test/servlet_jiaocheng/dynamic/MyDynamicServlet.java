package com.test.servlet_jiaocheng.dynamic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/29
 * Project: ServletDemo
 */
@WebServlet(name="MyDynamiceServlet",urlPatterns = {"/dynamic"},
initParams = {
        @WebInitParam(name="myCount",value="0")
})
public class MyDynamicServlet extends HttpServlet {
    private int myCount;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String incrNumStr = req.getParameter("incr");
        int incr = 1;
        if (null != incrNumStr) {
            incr = Integer.parseInt(incrNumStr);
        }

        myCount += incr;
        try{
            Thread.sleep(100);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setBufferSize(1024);
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write("<html><head></head><body>");
            writer.write("<h1>");
            writer.write(myCount+"");
            writer.write("</h1>");
            writer.write("</body></html>");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd=req.getRequestDispatcher("/first");
//        rd.forward(req,resp);//forward方法，client不知道转发
        resp.sendRedirect("/first");//这个会通知client转发请求,并且浏览器在此默认请求的话变为get请求
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        String viewCountStr=getInitParameter("myCount");
        myCount=Integer.parseInt(viewCountStr);
    }
}
