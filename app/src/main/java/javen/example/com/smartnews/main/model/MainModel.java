package javen.example.com.smartnews.main.model;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import javen.example.com.smartnews.main.fragment.ALittleVideoFragment;
import javen.example.com.smartnews.main.fragment.HomeFragment;
import javen.example.com.smartnews.main.fragment.PersonFragment;
import javen.example.com.smartnews.main.fragment.PlayVideoFragment;
import javen.example.com.smartnews.main.iinterface.IMainModel;

/**
 * Created by Javen on 10/11/2017.
 */

public class MainModel implements IMainModel {
    private List<Fragment> viewPagerDataList;

    public MainModel() {
        viewPagerDataList = new ArrayList<>();
    }

    @Override
    public List<Fragment> getViewPagerData() {
        viewPagerDataList.add(new HomeFragment());
        viewPagerDataList.add(new PlayVideoFragment());
        viewPagerDataList.add(new ALittleVideoFragment());
        viewPagerDataList.add(new PersonFragment());
        return viewPagerDataList;
    }
}
