package javen.example.com.news.main.fragment.home.presenter.top_news;

import java.util.List;

import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.mvp.fragment.presenter.BaseFragmentPresenter;
import javen.example.com.news.main.fragment.home.fragments.NewsFragment;
import javen.example.com.news.main.fragment.home.iinterface.top_news.INewsFragment;
import javen.example.com.news.main.fragment.home.iinterface.top_news.INewsModel;
import javen.example.com.news.main.fragment.home.iinterface.top_news.INewsPresenter;
import javen.example.com.news.main.fragment.home.model.top_news.NewsModel;


/**
 * Created by Javen on 17/11/2017.
 */

public class NewsPresenter extends BaseFragmentPresenter<NewsFragment> implements INewsPresenter<NewsBean> {
    private INewsFragment iNewsFragment;
    private INewsModel iNewsModel;

    public NewsPresenter(INewsFragment iNewsFragment) {
        this.iNewsFragment = iNewsFragment;
        this.iNewsModel = new NewsModel(NewsPresenter.this);
    }

    @Override
    public void requestNewsDataFromServer(INewsFragment<NewsBean> iNewsFragment, String type, String chineseNewsType) {
        iNewsModel.requestNewsDataFromServer(iNewsFragment, type,chineseNewsType);
    }

    public List<NewsBean> getNewsFromDataBase(String type) {

        return iNewsModel.queryAllObjectByType(type);
    }

}
