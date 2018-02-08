package javen.example.com.news.main.model;

import android.support.v4.app.Fragment;

import java.util.List;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.news.main.fragment.a_little_video.ALittleVideoFragment;
import javen.example.com.news.main.fragment.home.HomeFragment;
import javen.example.com.news.main.fragment.person.PersonFragment;
import javen.example.com.news.main.fragment.play_video.PlayVideoFragment;
import javen.example.com.news.main.iinterface.IMainModel;


/**
 * Created by Javen on 10/11/2017.
 */

public class MainModel implements IMainModel {
    private List<Fragment> viewPagerDataList;

    @Override
    public List<Fragment> getViewPagerData() {
        viewPagerDataList.add(new HomeFragment());
        viewPagerDataList.add(new PlayVideoFragment());
        viewPagerDataList.add(new ALittleVideoFragment());
        viewPagerDataList.add(new PersonFragment());
        return viewPagerDataList;
    }

    @Override
    public void deleteAllNewsData() {
        CommonLibraryApplication.daoSession.getNewsBeanDao().deleteAll();
    }
}
