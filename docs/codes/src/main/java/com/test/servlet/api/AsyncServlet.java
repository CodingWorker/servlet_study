package com.test.servlet.api;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/30
 * Project: my-servlet
 */
@WebServlet(
        urlPatterns = "/asyncservlet",
        initParams = {
        @WebInitParam(name="a",value = "aa")
},
asyncSupported = true//支持异步处理
)
public class AsyncServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //异步context
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                ServletResponse resp2=asyncContext.getResponse();
                try {
                    final PrintWriter writer = resp.getWriter();
                    writer.write("<html><head><title>异步处理请求测试</title></head><body>");
                    Date date = new Date();
                    writer.write("<h1>" + date + "</h1>");

                    //blocking operations


                    writer.write("</body></html>");
                    writer.flush();
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                asyncContext.complete();//complete the response and send it to client
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext acontext = req.startAsync();//获取异步context之后的处理都是在这个context上处理request和response

        final ServletInputStream input = req.getInputStream();

        input.setReadListener(new ReadListener() {//设置监听器，当数据能够被非阻塞的读取或者
            // 当数据读取完成或者出现错误的时候触发这个监听器。监听器需要提供回调函数
            byte buffer[] = new byte[4*1024];
            StringBuilder sbuilder = new StringBuilder();
            @Override
            public void onDataAvailable() {//回调函数，当数据可以被非阻塞的读取时触发
                try {
                    do {
                        int length = input.read(buffer);
                        sbuilder.append(new String(buffer, 0, length));
                    } while(input.isReady());
                } catch (IOException ex) { ... }
            }
            @Override
            public void onAllDataRead() {//回调函数，当所有的数据都可读时触发
                try {
                    acontext.getResponse().getWriter()
                            .write("...the response...");
                } catch (IOException ex) { ... }
                acontext.complete();
            }
            @Override
            public void onError(Throwable t) {//回调函数，当出现错误时触发
                ... }
        });

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println(config.getInitParameter("a"));
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
