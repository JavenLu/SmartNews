package javen.example.com.smartnews.main.fragment.home.model.top_news;

import java.util.List;

import javen.example.com.smartnews.MyApplication;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBeanDao;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.INewsModel;
import javen.example.com.smartnews.main.fragment.home.presenter.top_news.NewsPresenter;
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

public class NewsModel implements INewsModel<NewsBean> {
    private NewsPresenter newsPresenter;

    public NewsModel(NewsPresenter newsPresenter) {
        this.newsPresenter = newsPresenter;
    }

    @Override
    public void insertSingleObject(NewsBean object) {
        MyApplication.daoSession.getNewsBeanDao().insertOrReplaceInTx(object);
    }

    @Override
    public void deleteSingleObject(NewsBean object) {
        MyApplication.daoSession.getNewsBeanDao().delete(object);
    }

    @Override
    public void updateSingleObject(NewsBean object) {
        MyApplication.daoSession.getNewsBeanDao().update(object);
    }

    @Override
    public List<NewsBean> queryAllObject() {
        return MyApplication.daoSession.getNewsBeanDao().loadAll();
    }

    @Override
    public List<NewsBean> queryAllObjectByType(String type) {
        return MyApplication.daoSession.getNewsBeanDao().queryBuilder().where(NewsBeanDao.Properties.Type.eq(type)).list();
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
                newsPresenter.getTopNewsData(response);
            }

            @Override
            public void onFailure(Call<TopNewsResultBean> call, Throwable t) {
                newsPresenter.getTopNewsData(null);
            }
        });


    }

}
