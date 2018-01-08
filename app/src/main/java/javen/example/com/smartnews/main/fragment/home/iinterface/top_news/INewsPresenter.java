package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;


import java.util.List;

import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;

/**
 * Created by Javen on 17/11/2017.
 */

public interface INewsPresenter<T extends IDisplayNews> {
    public void requestNewsDataFromServer(INewsFragment<NewsBean> iNewsFragment, String type, String chineseNewsType);

    public List<T> getNewsFromDataBase(String type);
}
