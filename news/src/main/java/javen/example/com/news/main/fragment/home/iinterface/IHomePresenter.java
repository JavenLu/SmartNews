package javen.example.com.news.main.fragment.home.iinterface;

import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.bean.news.NewsChannelBean;


/**
 * Created by Javen on 15/11/2017.
 */

public interface IHomePresenter {

    public Map<String, List> getHomeFragments(List<NewsChannelBean> list);

}

