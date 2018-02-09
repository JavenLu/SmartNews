package javen.example.com.news.main.model.home;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsBeanDao;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBeanDao;

/**
 * Created by Javen on 09/02/2018.
 */

public class SearchHistoryModel {
    public static List<NewsSearchHistoryBean> queryHistoryKey(String key) {
        QueryBuilder<NewsSearchHistoryBean> queryBuilder = CommonLibraryApplication.daoSession.getNewsSearchHistoryBeanDao().queryBuilder();
        queryBuilder.where(NewsSearchHistoryBeanDao.Properties.NewsKey.eq(key)).build();
        return queryBuilder.list();
    }

    public static void insertHistoryKey(String key) {
        NewsSearchHistoryBean newsSearchHistoryBean = new NewsSearchHistoryBean();
        newsSearchHistoryBean.setNewsKey(key);
        CommonLibraryApplication.daoSession.getNewsSearchHistoryBeanDao().insert(newsSearchHistoryBean);
    }

    public static List<NewsBean> queryNewsByHistoryKey(String key) {
        QueryBuilder<NewsBean> queryBuilder = CommonLibraryApplication.daoSession.getNewsBeanDao().queryBuilder();
        queryBuilder.where(NewsBeanDao.Properties.Title.like(key)).build();
        return queryBuilder.list();
    }


}
