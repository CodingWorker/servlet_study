package com.test.servlet.api;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/9/30
 * Project: my-servlet
 */
@WebServlet(urlPatterns = {"/upload"},
initParams = {
        @WebInitParam(name="uploadDir",value = "F:")
})
@MultipartConfig(
//        location = "f:",//临时文件存放目录
//maxFileSize = 10,
//maxRequestSize = 20,
fileSizeThreshold = 5
)//接收文件
public class UploadFile extends HttpServlet{
    private String uploadDir;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.uploadDir=config.getInitParameter("uploadDir");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream in=null;
        BufferedInputStream bin=null;

        BufferedOutputStream bout=null;
        try {
            Part part = req.getPart("file");
            part.write("abc.txt");//使用part的方法

            //自己通过输入输出流来实现
            in = part.getInputStream();

            bin = new BufferedInputStream(in);
            String fileName= part.getSubmittedFileName();
            Path filePath = Paths.get(this.uploadDir, fileName);
            bout = new BufferedOutputStream(new FileOutputStream(filePath.toFile()));


            byte[] bytes = new byte[2048];
            StringBuilder sb = new StringBuilder();
            while (bin.read(bytes, 0, 2048) != -1) {
//            sb.append(new String(bytes,Charset.forName("utf-8"));
                bout.write(bytes, 0, 2048);
            }
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write("{\"code\":0}");
            writer.close();

        } catch (IOException ex) {
                ex.printStackTrace();
        }finally{
            if(null!=in){
                try{
                    in.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }

            if(null!=bin){
                try{
                    bin.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }

            if(null!=bout){
                try{
                    bout.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("{\"code\":1}");
        writer.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
