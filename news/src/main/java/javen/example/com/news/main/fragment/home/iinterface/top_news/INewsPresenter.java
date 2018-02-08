package javen.example.com.news.main.fragment.home.iinterface.top_news;


import java.util.List;

import javen.example.com.commonlibrary.bean.news.NewsBean;


/**
 * Created by Javen on 17/11/2017.
 */

public interface INewsPresenter<T> {
    public void requestNewsDataFromServer(INewsFragment<NewsBean> iNewsFragment, String type, String chineseNewsType);

    public List<T> getNewsFromDataBase(String type);
}
