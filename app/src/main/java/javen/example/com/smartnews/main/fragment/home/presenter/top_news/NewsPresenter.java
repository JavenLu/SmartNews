package javen.example.com.smartnews.main.fragment.home.presenter.top_news;

import java.util.List;

import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;
import javen.example.com.smartnews.main.fragment.home.fragments.NewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.INewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.INewsModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.INewsPresenter;
import javen.example.com.smartnews.main.fragment.home.model.top_news.NewsModel;
import javen.example.com.smartnews.net.top_news.NewsResultBean;
import javen.example.com.smartnews.net.top_news.TransferDataInterface;
import javen.example.com.smartnews.utils.CheckUtil;
import retrofit2.Response;

/**
 * Created by Javen on 17/11/2017.
 */

public class NewsPresenter extends BaseFragmentPresenter<NewsFragment> implements INewsPresenter<NewsBean>, TransferDataInterface<NewsBean> {
    private INewsFragment iNewsFragment;
    private INewsModel iNewsModel;

    public NewsPresenter(INewsFragment iNewsFragment) {
        this.iNewsFragment = iNewsFragment;
        this.iNewsModel = new NewsModel(NewsPresenter.this);
    }

    @Override
    public void getTopNewsData(Response<NewsResultBean> response) {

        if (CheckUtil.getInstance().isCheckResponseAvailable(response)) {

            if (CheckUtil.getInstance().isCheckTopNewsListNotNull(response)) {

                iNewsFragment.getTopNewsData(response.body().getResult().getData());
            } else {
                iNewsFragment.getTopNewsData(null);
            }

        } else {
            iNewsFragment.getTopNewsData(null);
        }


    }


    @Override
    public void requestTopNewsDataFromServer() {
        iNewsModel.requestTopNewsDataFromServer();
    }

    @Override
    public void insertTopNewsListIntoDataBase(List<NewsBean> list) {

        if (CheckUtil.getInstance().isCheckListUsable(list)) {
            for (NewsBean newsBean : list) {
                iNewsModel.insertSingleObject(newsBean);
            }
        }

    }


    @Override
    public List<NewsBean> getAllTopNewsFromDataBase() {

        return iNewsModel.queryAllObject();
    }

}
