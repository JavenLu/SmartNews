package javen.example.com.news.main.model.home;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsBeanDao;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBeanDao;
import javen.example.com.news.main.iinterface.home.INewsSearchModel;

/**
 * Created by Javen on 19/01/2018.
 */

public class NewsSearchModel implements INewsSearchModel {

    @Override
    public List<NewsSearchHistoryBean> getHistorySearchKeyFromDataBase(String searchKey) {
        QueryBuilder<NewsSearchHistoryBean> queryBuilder = CommonLibraryApplication.daoSession.getNewsSearchHistoryBeanDao().queryBuilder();
        queryBuilder.where(NewsSearchHistoryBeanDao.Properties.NewsKey.like(searchKey)).build();
        return queryBuilder.list();
    }

    @Override
    public List<NewsBean> getNewsListBySearchHistoryKey(String searchKey) {
        QueryBuilder<NewsBean> queryBuilder = CommonLibraryApplication.daoSession.getNewsBeanDao().queryBuilder();
        queryBuilder.where(NewsBeanDao.Properties.Title.like(searchKey)).build();
        return queryBuilder.list();
    }

    @Override
    public void insertSearchKeyToDataBase(String searchKey) {
        NewsSearchHistoryBean newsSearchHistoryBean = new NewsSearchHistoryBean();
        newsSearchHistoryBean.setNewsKey(searchKey);
        CommonLibraryApplication.daoSession.getNewsSearchHistoryBeanDao().insert(newsSearchHistoryBean);
    }
}
