package javen.example.com.smartnews.main.fragment.home.presenter.top_news;

import java.util.List;

import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;
import javen.example.com.smartnews.main.fragment.home.fragments.NewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.INewsModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsPresenter;
import javen.example.com.smartnews.main.fragment.home.model.top_news.NewsModel;
import javen.example.com.smartnews.net.top_news.TopNewsResultBean;
import javen.example.com.smartnews.net.top_news.TransferDataInterface;
import javen.example.com.smartnews.utils.CheckUtil;
import retrofit2.Response;

/**
 * Created by Javen on 17/11/2017.
 */

public class NewsPresenter extends BaseFragmentPresenter<NewsFragment> implements ITopNewsPresenter<NewsBean>, TransferDataInterface<NewsBean> {
    private ITopNewsFragment iTopNewsFragment;
    private INewsModel iNewsModel;

    public NewsPresenter(ITopNewsFragment iTopNewsFragment) {
        this.iTopNewsFragment = iTopNewsFragment;
        this.iNewsModel = new NewsModel(NewsPresenter.this);
    }

    @Override
    public void getTopNewsData(Response<TopNewsResultBean> response) {

        if (CheckUtil.getInstance().isCheckResponseAvailable(response)) {

            if (CheckUtil.getInstance().isCheckTopNewsListNotNull(response)) {

                iTopNewsFragment.getTopNewsData(response.body().getResult().getData());
            } else {
                iTopNewsFragment.getTopNewsData(null);
            }

        } else {
            iTopNewsFragment.getTopNewsData(null);
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
