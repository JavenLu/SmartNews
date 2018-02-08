package javen.example.com.news.main.fragment.home.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsChannelBean;
import javen.example.com.news.main.fragment.home.fragments.NewsFragment;
import javen.example.com.news.main.fragment.home.iinterface.IHomeModel;


/**
 * Created by Javen on 15/11/2017.
 */

public class HomeModel implements IHomeModel {
    public static final String FRAGMENT_LIST = "fragmentList";
    public static final String CHANNEL_NAME_LIST = "channelNameList";

    @Override
    public Map<String, List> getHomeFragments(List<NewsChannelBean> list) {
        Map<String, List> map = null;
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> channelNameList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            map = new HashMap<>();

            for (NewsChannelBean newsChannelBean : list) {
                NewsFragment newsFragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("newsType", CommonLibraryApplication.typeTreeMap.get(newsChannelBean.getNewsChannelName()));
                bundle.putString("chineseNewsType", newsChannelBean.getNewsChannelName());
                newsFragment.setArguments(bundle);
                fragmentList.add(newsFragment);
                channelNameList.add(newsChannelBean.getNewsChannelName());
            }

            map.put(FRAGMENT_LIST, fragmentList);
            map.put(CHANNEL_NAME_LIST, channelNameList);
        }


        return map;
    }


}
