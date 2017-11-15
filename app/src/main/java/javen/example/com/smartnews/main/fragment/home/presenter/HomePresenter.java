package javen.example.com.smartnews.main.fragment.home.presenter;

import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomePresenter;
import javen.example.com.smartnews.main.fragment.home.model.HomeModel;

/**
 * Created by Javen on 15/11/2017.
 */

public class HomePresenter extends BaseFragmentPresenter<BaseFragment> implements IHomePresenter{
    IHomeFragment iHomeFragment;
    IHomeModel iHomeModel;

    public HomePresenter(IHomeFragment iHomeFragment) {
        this.iHomeFragment = iHomeFragment;
        iHomeModel = new HomeModel();
    }

}
