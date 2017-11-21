package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;

import java.util.List;

import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;

/**
 * Created by Javen on 17/11/2017.
 */

public interface ITopNewsFragment<E> {
    public void getTopNewsData(List<TopNewsBean> list);
}
