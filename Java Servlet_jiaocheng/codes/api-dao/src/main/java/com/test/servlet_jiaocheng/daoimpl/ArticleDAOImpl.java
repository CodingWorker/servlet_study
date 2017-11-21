package com.test.servlet_jiaocheng.daoimpl;

import com.test.servlet_jiaocheng.dao.IArticleDAO;
import com.test.servlet_jiaocheng.model.Article;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class ArticleDAOImpl implements IArticleDAO {
    private static final String TABLE_NAME="company";
    private static final String CONNECTION_URL="jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user="root";
    private static final String password="1234.asd";
    public List<Article> listArticles(int page, int size, boolean containDeleted) {
        List<Article> articles = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE deleted=0 LIMIT ?,?";
            if (containDeleted) sql = "SELECT * FROM " + TABLE_NAME + " LIMIT ?,?;";

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(CONNECTION_URL,user,password);

            ps = conn.prepareStatement(sql);
            ps.setInt(1,page);
            ps.setInt(2,size);
            rs = ps.executeQuery();
            articles = rsToList(rs);
        } catch (SQLException exx) {
            exx.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }

        return articles;
    }

    private List<Article> rsToList(ResultSet rs)throws SQLException{
        List<Article> articles=null;
        if(rs!=null){
            while(rs.next()){
                if(articles==null) articles=new ArrayList<>();
                Article article=new Article();
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String faDingDaiBiao=rs.getString("fading_daibiao");
                String zhuCeZiBen=rs.getString("zhuce_ziben");
                String chengLiRiQi=rs.getString("chengli_riqi");
                String lianXiDianHua=rs.getString("lianxi_dianhua");
                String addres=rs.getString("address");
                String jingYingFanWei=rs.getString("jingying_fanwei");
                String email=rs.getString("email");
                String site=rs.getString("site");
                boolean deleted=rs.getBoolean("deleted");
                Timestamp createTime=rs.getTimestamp("create_time");
                Timestamp updateTime=rs.getTimestamp("update_time");
                article.setId(id);
                article.setName(name);
                article.setFaDingDaiBiao(faDingDaiBiao);
                article.setZhuCeZiBen(zhuCeZiBen);
                article.setChengLiRiQi(chengLiRiQi);
                article.setAddress(addres);
                article.setJingYingFanWei(jingYingFanWei);
                article.setEmail(email);
                article.setSite(site);
                article.setDeleted(deleted);
                article.setCreateTime(createTime);
                article.setUpdateTime(updateTime);

                articles.add(article);
            }
        }

        return articles;
    }

}
