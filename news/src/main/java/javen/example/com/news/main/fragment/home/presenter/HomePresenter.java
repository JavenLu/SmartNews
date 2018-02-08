package javen.example.com.news.main.fragment.home.presenter;

import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.bean.news.NewsChannelBean;
import javen.example.com.commonlibrary.mvp.fragment.presenter.BaseFragmentPresenter;
import javen.example.com.commonlibrary.mvp.fragment.view.BaseFragment;
import javen.example.com.news.main.fragment.home.iinterface.IHomeFragment;
import javen.example.com.news.main.fragment.home.iinterface.IHomeModel;
import javen.example.com.news.main.fragment.home.iinterface.IHomePresenter;
import javen.example.com.news.main.fragment.home.model.HomeModel;


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
    public Map<String, List> getHomeFragments(List<NewsChannelBean> list) {
        return iHomeModel.getHomeFragments(list);
    }

}
