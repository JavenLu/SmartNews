package javen.example.com.smartnews.main.fragment.home.iinterface;

import android.support.v4.app.Fragment;

import java.util.List;

import javen.example.com.smartnews.db.news_channel.NewsChannelBean;

/**
 * Created by Javen on 15/11/2017.
 */

public interface IHomePresenter {

    public List<Fragment> getHomeFragments(List<NewsChannelBean> list);

}
