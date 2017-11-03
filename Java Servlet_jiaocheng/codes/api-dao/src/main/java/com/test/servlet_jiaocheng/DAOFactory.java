package com.test.servlet_jiaocheng;

import com.test.servlet_jiaocheng.dao.IArticleDAO;
import com.test.servlet_jiaocheng.daoimpl.ArticleDAOImpl;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class DAOFactory {
    private static final DAOFactory DAO_FACTORY=new DAOFactory();
    private final IArticleDAO articleDAO=new ArticleDAOImpl();
    public static DAOFactory getInstance(){
        return DAO_FACTORY;
    }

    public IArticleDAO articleDAO(){
        return articleDAO;
    }


}
