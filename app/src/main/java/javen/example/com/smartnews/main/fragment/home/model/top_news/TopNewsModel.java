package javen.example.com.smartnews.main.fragment.home.model.top_news;

import java.util.List;

import javen.example.com.smartnews.MyApplication;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBeanDao;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsModel;
import javen.example.com.smartnews.main.fragment.home.presenter.top_news.TopNewsPresenter;
import javen.example.com.smartnews.net.NetConstants;
import javen.example.com.smartnews.net.top_news.GetRequestTopNewsInterface;
import javen.example.com.smartnews.net.top_news.TopNewsResultBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsModel implements ITopNewsModel<TopNewsBean> {
    private TopNewsPresenter topNewsPresenter;

    public TopNewsModel(TopNewsPresenter topNewsPresenter) {
        this.topNewsPresenter = topNewsPresenter;
    }

    @Override
    public void insertSingleObject(TopNewsBean object) {
        MyApplication.daoSession.getTopNewsBeanDao().insertOrReplaceInTx(object);
    }

    @Override
    public void deleteSingleObject(TopNewsBean object) {
        MyApplication.daoSession.getTopNewsBeanDao().delete(object);
    }

    @Override
    public void updateSingleObject(TopNewsBean object) {
        MyApplication.daoSession.getTopNewsBeanDao().update(object);
    }

    @Override
    public List<TopNewsBean> queryAllObject() {
        return MyApplication.daoSession.getTopNewsBeanDao().loadAll();
    }

    @Override
    public List<TopNewsBean> queryAllObjectByType(String type) {
        return MyApplication.daoSession.getTopNewsBeanDao().queryBuilder().where(TopNewsBeanDao.Properties.Type.eq(type)).list();
    }

    @Override
    public void requestTopNewsDataFromServer() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequestTopNewsInterface getRequestTopNewsInterface = retrofit.create(GetRequestTopNewsInterface.class);
        Call<TopNewsResultBean> call = getRequestTopNewsInterface.getTopNewsResult(NetConstants.AUTHORIZATION, NetConstants.TOP_NEWS_TYPE);
        call.enqueue(new Callback<TopNewsResultBean>() {
            @Override
            public void onResponse(Call<TopNewsResultBean> call, Response<TopNewsResultBean> response) {
                topNewsPresenter.getTopNewsData(response);
            }

            @Override
            public void onFailure(Call<TopNewsResultBean> call, Throwable t) {
                topNewsPresenter.getTopNewsData(null);
            }
        });


    }

}
