package javen.example.com.smartnews.main.fragment.home.presenter.top_news;

import java.util.List;

import javen.example.com.smartnews.db.GreenDaoManager;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import javen.example.com.smartnews.main.fragment.home.fragments.TopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsModel;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsPresenter;
import javen.example.com.smartnews.main.fragment.home.model.top_news.TopNewsModel;
import javen.example.com.smartnews.net.top_news.TopNewsResultBean;
import javen.example.com.smartnews.net.top_news.TransferDataInterface;
import javen.example.com.smartnews.utils.CheckUtil;
import retrofit2.Response;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsPresenter extends BaseFragmentPresenter<TopNewsFragment> implements ITopNewsPresenter<TopNewsBean>, TransferDataInterface<TopNewsBean> {
    private ITopNewsFragment iTopNewsFragment;
    private ITopNewsModel iTopNewsModel;

    public TopNewsPresenter(ITopNewsFragment iTopNewsFragment) {
        this.iTopNewsFragment = iTopNewsFragment;
        this.iTopNewsModel = new TopNewsModel(TopNewsPresenter.this);
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
        iTopNewsModel.requestTopNewsDataFromServer();
    }

    @Override
    public void insertTopNewsListIntoDataBase(List<TopNewsBean> list) {

        if (CheckUtil.getInstance().isCheckListUsable(list)) {
            for (TopNewsBean topNewsBean : list) {
                iTopNewsModel.insertSingleObject(topNewsBean);
            }
        }

    }


    @Override
    public List<TopNewsBean> getAllTopNewsFromDataBase() {

        return iTopNewsModel.queryAllObject();
    }

}
