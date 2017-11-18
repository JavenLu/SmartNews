package javen.example.com.smartnews.main.fragment.home.presenter.top_news;

import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.fragments.TopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsPresenter;
import javen.example.com.smartnews.main.fragment.home.model.top_news.TopNewsModel;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsPresenter extends BaseFragmentPresenter<TopNewsFragment> implements ITopNewsPresenter {
    private ITopNewsFragment iTopNewsFragment;
    private ITopNewsModel iTopNewsModel;

    public TopNewsPresenter(ITopNewsFragment iTopNewsFragment) {
        this.iTopNewsFragment = iTopNewsFragment;
        this.iTopNewsModel = new TopNewsModel();
    }

}
