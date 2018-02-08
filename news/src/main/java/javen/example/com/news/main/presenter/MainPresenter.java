package javen.example.com.news.main.presenter;


import javen.example.com.commonlibrary.mvp.activity.presenter.BasePresenter;
import javen.example.com.news.main.activity.MainActivity;
import javen.example.com.news.main.iinterface.IMainActivity;
import javen.example.com.news.main.iinterface.IMainModel;
import javen.example.com.news.main.iinterface.IMainPresenter;
import javen.example.com.news.main.model.MainModel;

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
    public void deleteAllNewsData() {
        iMainModel.deleteAllNewsData();
    }
}
