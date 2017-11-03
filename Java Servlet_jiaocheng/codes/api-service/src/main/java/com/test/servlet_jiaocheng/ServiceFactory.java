package com.test.servlet_jiaocheng;

import com.test.servlet_jiaocheng.service.IArticleService;
import com.test.servlet_jiaocheng.serviceimpl.ArticleServiceImpl;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public final class ServiceFactory {
    private static final ServiceFactory SERVICE_FACTORY=new ServiceFactory();
    private static final IArticleService ARTICLE_SERVICE=new ArticleServiceImpl();

    public static ServiceFactory getInstance(){
        return SERVICE_FACTORY;
    }

    public IArticleService articleService(){
        return ARTICLE_SERVICE;
    }

}
