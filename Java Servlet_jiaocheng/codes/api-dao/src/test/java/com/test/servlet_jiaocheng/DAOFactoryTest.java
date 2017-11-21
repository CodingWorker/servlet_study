package com.test.servlet_jiaocheng;

import com.test.servlet_jiaocheng.dao.IArticleDAO;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class DAOFactoryTest {
    @Test
    public void getDAOTest(){
        IArticleDAO articleDAO=DAOFactory.getInstance().articleDAO();
        articleDAO.listArticles(0,10,true);
    }

}
