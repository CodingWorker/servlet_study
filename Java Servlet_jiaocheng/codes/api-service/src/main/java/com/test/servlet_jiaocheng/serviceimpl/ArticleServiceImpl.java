package com.test.servlet_jiaocheng.serviceimpl;

import com.test.servlet_jiaocheng.DAOFactory;
import com.test.servlet_jiaocheng.dao.IArticleDAO;
import com.test.servlet_jiaocheng.model.Article;
import com.test.servlet_jiaocheng.service.IArticleService;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class ArticleServiceImpl implements IArticleService {
    private final IArticleDAO articleDAO=DAOFactory.getInstance().articleDAO();
    public List<Article> listArticles(int page, int size, boolean containDeleted){
        return  articleDAO.listArticles(page,size,true);
    }
}
