package javen.example.com.news.main.iinterface.home;

import java.util.List;

import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;


/**
 * Created by Javen on 19/01/2018.
 */

public interface INewsSearchModel {
    List<NewsSearchHistoryBean> getHistorySearchKeyFromDataBase(String searchKey);
    List<NewsBean> getNewsListBySearchHistoryKey(String searchKey);
    void insertSearchKeyToDataBase(String searchKey);
}
