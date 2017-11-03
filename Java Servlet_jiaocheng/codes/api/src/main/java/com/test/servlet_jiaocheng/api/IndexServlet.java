package com.test.servlet_jiaocheng.api;

import com.test.servlet_jiaocheng.ServiceFactory;
import com.test.servlet_jiaocheng.contants.MIMEType;
import com.test.servlet_jiaocheng.model.Article;
import com.test.servlet_jiaocheng.service.IArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
@WebServlet(name="index",urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        String p1= config.getInitParameter("a");
//        System.out.println(p1);
//    }


    /**
     * 获取文章列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IArticleService articleService = ServiceFactory.getInstance().articleService();
        int page=0;
        int pageSize=10;
        String pageStr=req.getParameter("page");
        if(pageStr!=null) page=Integer.valueOf(pageStr);
        String pageSizeStr=req.getParameter("pageSize");
        if(pageSizeStr!=null) pageSize=Integer.valueOf(pageSizeStr);

        List<Article> articles= articleService.listArticles(page,pageSize,false);

        ApiResult apiResult=new ApiResult();
        apiResult.setCode(0);
        apiResult.setMsg("success");
        apiResult.setData(articles);

        resp.setHeader("Content-Type", MIMEType.APPLICATION_JSON.typeValue());
        resp.setHeader("Status Code",200+"");
        OutputStream out=resp.getOutputStream();
        PrintWriter pw=new PrintWriter(out);
        try{
            pw.write(apiResult.toJsonString());
            pw.flush();
        }finally {
            out.close();
            pw.close();
        }

    }
}
