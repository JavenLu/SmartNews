package javen.example.com.news.main.presenter.home;

import java.util.List;

import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.mvp.activity.presenter.BasePresenter;
import javen.example.com.news.main.activity.home.NewsSearchActivity;
import javen.example.com.news.main.iinterface.home.INewsSearchActivity;
import javen.example.com.news.main.iinterface.home.INewsSearchModel;
import javen.example.com.news.main.iinterface.home.INewsSearchPresenter;
import javen.example.com.news.main.model.home.NewsSearchModel;


/**
 * Created by Javen on 19/01/2018.
 */

public class NewsSearchPresenter extends BasePresenter<NewsSearchActivity> implements INewsSearchPresenter {
    private INewsSearchActivity iNewsSearchActivity;
    private INewsSearchModel iNewsSearchModel;

    public NewsSearchPresenter(INewsSearchActivity iNewsSearchActivity) {
        this.iNewsSearchActivity = iNewsSearchActivity;
        iNewsSearchModel = new NewsSearchModel();
    }

    @Override
    public void initData() {

    }

    @Override
    public List<NewsSearchHistoryBean> getHistorySearchKeyFromDataBase(String searchKey) {
        return iNewsSearchModel.getHistorySearchKeyFromDataBase(searchKey);
    }

    @Override
    public List<NewsBean> getNewsListBySearchNewsDataBase(String searchKey) {
        return iNewsSearchModel.getNewsListBySearchHistoryKey(searchKey);
    }

    @Override
    public void insertSearchKeyToDataBase(String searchKey) {
        iNewsSearchModel.insertSearchKeyToDataBase(searchKey);
    }
}
