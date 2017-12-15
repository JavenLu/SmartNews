package javen.example.com.smartnews.main.fragment.home.iinterface;


import java.util.List;
import java.util.Map;

import javen.example.com.smartnews.db.news_channel.NewsChannelBean;

/**
 * Created by Javen on 15/11/2017.
 */

public interface IHomeModel {
    public Map<String, List> getHomeFragments(List<NewsChannelBean> list);
}
