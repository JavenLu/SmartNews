package javen.example.com.smartnews.main.fragment.home.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javen.example.com.smartnews.MyApplication;
import javen.example.com.smartnews.R;
import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.fragment.home.fragments.NewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeModel;

/**
 * Created by Javen on 15/11/2017.
 */

public class HomeModel implements IHomeModel {

//    @Override
//    public List<Fragment> getHomeFragments() {
//        List<Fragment> list = new ArrayList<>();
//        HashMap<String, Fragment> typeTreeMap = MyApplication.typeTreeMap;
//
//        for (Object o : typeTreeMap.entrySet()) {
//            Map.Entry entry = (Map.Entry) o;
//            list.add((Fragment) entry.getValue());
//        }
//
//        return list;
//    }


    @Override
    public List<Fragment> getHomeFragments(List<NewsChannelBean> list) {
        List<Fragment> fragmentList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (NewsChannelBean newsChannelBean : list) {
                NewsFragment newsFragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("newsType", MyApplication.typeTreeMap.get(newsChannelBean.getNewsChannelName()));
                bundle.putString("chineseNewsType", newsChannelBean.getNewsChannelName());
                newsFragment.setArguments(bundle);
                fragmentList.add(newsFragment);
            }

        }

        return fragmentList;
    }
}
