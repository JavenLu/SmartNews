package javen.example.com.smartnews.main.presenter;

import android.support.v4.app.Fragment;

import java.util.List;

import javen.example.com.smartnews.BasePresenter;
import javen.example.com.smartnews.main.activity.MainActivity;
import javen.example.com.smartnews.main.iinterface.IMainActivity;
import javen.example.com.smartnews.main.iinterface.IMainModel;
import javen.example.com.smartnews.main.iinterface.IMainPresenter;
import javen.example.com.smartnews.main.model.MainModel;

/**
 * Created by Javen on 10/11/2017.
 */

public class MainPresenter extends BasePresenter<MainActivity> implements IMainPresenter {
    private IMainActivity iMainActivity;
    private IMainModel iMainModel;

    public MainPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        iMainModel = new MainModel();
    }

    @Override
    public void initData() {

    }

    @Override
    public List<Fragment> getViewPagerData() {

        return iMainModel.getViewPagerData();
    }
}
