package javen.example.com.smartnews.main.fragment.home.presenter.top_news;

import android.text.TextUtils;

import java.util.List;

import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import javen.example.com.smartnews.main.fragment.home.fragments.TopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsPresenter;
import javen.example.com.smartnews.main.fragment.home.model.top_news.TopNewsModel;
import javen.example.com.smartnews.net.NetConstants;
import javen.example.com.smartnews.net.top_news.TopNewsResultBean;
import javen.example.com.smartnews.net.top_news.TransferDataInterface;
import retrofit2.Response;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsPresenter extends BaseFragmentPresenter<TopNewsFragment> implements ITopNewsPresenter, TransferDataInterface<TopNewsBean> {
    private ITopNewsFragment iTopNewsFragment;
    private ITopNewsModel iTopNewsModel;

    public TopNewsPresenter(ITopNewsFragment iTopNewsFragment) {
        this.iTopNewsFragment = iTopNewsFragment;
        this.iTopNewsModel = new TopNewsModel(TopNewsPresenter.this);
    }

    @Override
    public void getTopNewsData(Response<TopNewsResultBean> response) {

        if (!TextUtils.isEmpty(response.body().getReason()) && NetConstants.SUCCESS_REASON.equals(response.body().getReason())) {

            if (isCheckTopNewsListNotNull(response)) {

                iTopNewsFragment.getTopNewsData(response.body().getResult().getData());
            } else {
                iTopNewsFragment.getTopNewsData(null);
            }

        } else {
            iTopNewsFragment.getTopNewsData(null);
        }


    }

    @Override
    public void insertTopNewsListIntoDataBase(List<TopNewsBean> list) {
        for (TopNewsBean topNewsBean : list) {
            iTopNewsModel.insertSingleObject(topNewsBean);
        }

    }

    private boolean isCheckTopNewsListNotNull(Response<TopNewsResultBean> response) {
        return response.body().getResult().getData() != null && response.body().getResult().getData().size() > 0;
    }

    @Override
    public void requestTopNewsDataFromServer() {
        iTopNewsModel.requestTopNewsDataFromServer();
    }
}
