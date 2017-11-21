package com.test.servlet_jiaocheng;

import com.test.servlet_jiaocheng.service.IArticleService;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/11/3
 * Project: ServletDemo
 */
public class ServiceFactoryTest {
    @Test
    public void getServiceTest(){
        IArticleService articleService=ServiceFactory.getInstance().articleService();
        articleService.listArticles(0,10,true);
    }
}
