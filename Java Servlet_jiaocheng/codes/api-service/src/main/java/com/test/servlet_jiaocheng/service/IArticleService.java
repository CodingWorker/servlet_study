package com.test.servlet_jiaocheng.service;

import com.test.servlet_jiaocheng.model.Article;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public interface IArticleService {
    List<Article> listArticles(int page,int size,boolean containDeleted);
}
