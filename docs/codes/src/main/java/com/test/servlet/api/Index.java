package com.test.servlet.api;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@WebServlet(urlPatterns = {"/index"},asyncSupported = true,initParams = {
        @WebInitParam(name = "a",value = "aa"),
        @WebInitParam(name = "b",value = "bb")
})
public class Index extends HttpServlet{

    //初始化正在运行的服务的字段，即正在处理的请求的个数，也是正在处理请求的线程的个数
    //这个字段，可以用在当调用destroy方法查看当前正在运行的线程个数以便干净的关闭servlet。
    private int runningCount;

    //判断当前犯法是否要被要被关闭
    private boolean shuttingDown;

    private int shutdownInterval=10000;

    @Override
    public void init() throws ServletException {
        Enumeration<String> initParamsEnum=getInitParameterNames();
        while(initParamsEnum.hasMoreElements()){
            System.out.println(initParamsEnum.nextElement());
        }

        this.runningCount=0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        HttpSession session=req.getSession();
        String testSession= (String)session.getAttribute("test");

        //get and set session的timeout
        System.out.println(session.getMaxInactiveInterval());
        session.setMaxInactiveInterval(1000);
        System.out.println(testSession);

        //对于一次性访问，我们可以手动删除session在我们不需要的时候
        session.invalidate();

        //如果client禁用了cookie可以通过调用encode方法来在url上添加sessionId
        resp.encodeURL(req.getRequestURI());

        resp.setContentType("text/html");//set before response commited
        resp.setBufferSize(2048);//设置缓冲区的大小，默认的会将响应的任何输出直接输出到client
        PrintWriter writer=resp.getWriter();//返回character data
        writer.write("hello servlet\n");
        writer.write("Thread: "+Thread.currentThread().getName());
        writer.write(resp.encodeURL(req.getRequestURI()));
        writer.close();
        resp.flushBuffer();
    }

    private void helloGetResponse(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        RequestDispatcher dispatcher= req.getRequestDispatcher("/greeting/hello");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException {
//        resp.setContentType("application/json");
//        resp.setBufferSize(2048);
//        OutputStream outputStream=resp.getOutputStream();
//        outputStream.write("hello servlet".getBytes());
//        outputStream.close();
//
//        resp.flushBuffer();

        //long-running  example
        for (int i = 0; ((i < 10000) &&
                !isShuttingDown()); i++) {
            try {
                //many stuff to do
            } catch (Exception e) {
                //deal with exception
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.incrRunngingCount();//记录一条进入的请求
        super.service(req, resp);
    }

    synchronized private void incrRunngingCount(){
        this.runningCount++;
    }

    synchronized private void decrRunningCount(){
        this.runningCount--;
    }

    //Access methods for shuttingDown
    //这个方法需要被其他服务线程每个一段时间调用以确定servlet是否要被关闭以
    //做出对应的处理
    protected synchronized void setShuttingDown(boolean flag) {
        shuttingDown = flag;
    }
    protected synchronized boolean isShuttingDown() {
        return shuttingDown;
    }

    @Override
    public void destroy(){
        /* Check to see whether there are still service methods /*
        /* running, and if there are, tell them to stop. */
        if (this.runningCount > 0) {
            setShuttingDown(true);
        }

        /* Wait for the service methods to stop. */
        while (this.runningCount > 0) {
            try {
                Thread.sleep(this.shutdownInterval);
            } catch (InterruptedException e) {
            }

        }
    }
}
