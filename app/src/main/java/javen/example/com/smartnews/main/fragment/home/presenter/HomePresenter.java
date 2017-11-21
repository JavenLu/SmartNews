package javen.example.com.smartnews.main.fragment.home.presenter;

import android.support.v4.app.Fragment;

import java.util.List;

import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomePresenter;
import javen.example.com.smartnews.main.fragment.home.model.HomeModel;
import javen.example.com.smartnews.net.top_news.TransferDataInterface;

/**
 * Created by Javen on 15/11/2017.
 */

public class HomePresenter extends BaseFragmentPresenter<BaseFragment> implements IHomePresenter {
    IHomeFragment iHomeFragment;
    IHomeModel iHomeModel;

    public HomePresenter(IHomeFragment iHomeFragment) {
        this.iHomeFragment = iHomeFragment;
        iHomeModel = new HomeModel();
    }

    @Override
    public List<Fragment> getHomeFragments() {
        return iHomeModel.getHomeFragments();
    }

}
