package javen.example.com.smartnews.main.presenter.home;

import javen.example.com.smartnews.BasePresenter;
import javen.example.com.smartnews.main.activity.home.NewsSearchActivity;
import javen.example.com.smartnews.main.iinterface.home.INewsSearchActivity;
import javen.example.com.smartnews.main.iinterface.home.INewsSearchModel;
import javen.example.com.smartnews.main.iinterface.home.INewsSearchPresenter;
import javen.example.com.smartnews.main.model.home.NewsSearchModel;

/**
 * Created by Javen on 19/01/2018.
 */

public class NewsSearchPresenter extends BasePresenter<NewsSearchActivity> implements INewsSearchPresenter {
    private INewsSearchActivity iNewsSearchActivity;
    private INewsSearchModel iNewsSearchModel;

    public NewsSearchPresenter(INewsSearchActivity iNewsSearchActivity) {
        this.iNewsSearchActivity = iNewsSearchActivity;
        iNewsSearchModel = new NewsSearchModel();
    }

    @Override
    public void initData() {

    }
}
