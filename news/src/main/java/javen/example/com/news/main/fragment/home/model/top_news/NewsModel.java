package javen.example.com.news.main.fragment.home.model.top_news;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsBeanDao;
import javen.example.com.commonlibrary.net.GetRequestTopNewsInterface;
import javen.example.com.commonlibrary.net.NetConstants;
import javen.example.com.commonlibrary.net.NewsResultBean;
import javen.example.com.commonlibrary.utils.CheckUtil;
import javen.example.com.commonlibrary.utils.RxJavaTransformerUtil;
import javen.example.com.news.main.fragment.home.iinterface.top_news.INewsFragment;
import javen.example.com.news.main.fragment.home.iinterface.top_news.INewsModel;
import javen.example.com.news.main.fragment.home.presenter.top_news.NewsPresenter;
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
        CommonLibraryApplication.daoSession.getNewsBeanDao().insertOrReplaceInTx(object);
    }

    @Override
    public void deleteSingleObject(NewsBean object) {
        CommonLibraryApplication.daoSession.getNewsBeanDao().delete(object);
    }

    @Override
    public void deleteAllData() {
        CommonLibraryApplication.daoSession.getNewsBeanDao().deleteAll();
    }

    @Override
    public void updateSingleObject(NewsBean object) {
        CommonLibraryApplication.daoSession.getNewsBeanDao().update(object);
    }

    @Override
    public List<NewsBean> queryAllObject() {
        return CommonLibraryApplication.daoSession.getNewsBeanDao().loadAll();
    }

    @Override
    public List<NewsBean> queryAllObjectByType(String type) {
        return CommonLibraryApplication.daoSession.getNewsBeanDao().queryBuilder().where(NewsBeanDao.Properties.Category.eq(type)).list();
    }

    @Override
    public void requestNewsDataFromServer(INewsFragment<NewsBean> iNewsFragment, String type, String chineseNewsType) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequestTopNewsInterface getRequestTopNewsInterface = retrofit.create(GetRequestTopNewsInterface.class);
        Call<NewsResultBean> call = getRequestTopNewsInterface.getTopNewsResult(NetConstants.AUTHORIZATION, type);
        call.enqueue(new Callback<NewsResultBean>() {
            @Override
            public void onResponse(Call<NewsResultBean> call, Response<NewsResultBean> response) {
                storeDataAndGetIt(iNewsFragment, response, chineseNewsType);
            }

            @Override
            public void onFailure(Call<NewsResultBean> call, Throwable t) {
                iNewsFragment.getTopNewsData(null);
            }
        });


    }

    @Override
    public void storeDataAndGetIt(INewsFragment<NewsBean> iNewsFragment, Response<NewsResultBean> response, String chineseNewsType) {

        Observable.create((ObservableOnSubscribe<List<NewsBean>>) emitter -> {
            parseDataFromServerAndInsertDB(response);
            List<NewsBean> result = queryAllObjectByType(chineseNewsType);

            emitter.onNext(result);
            emitter.onComplete();
        }).compose(RxJavaTransformerUtil.applyDefaultSchedulers())
                .subscribe(new Observer<List<NewsBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NewsBean> newsBeans) {
                        if (newsBeans != null) {
                            iNewsFragment.getTopNewsData(newsBeans);
                        } else {
                            iNewsFragment.getTopNewsData(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void parseDataFromServerAndInsertDB(Response<NewsResultBean> response) {
        if (CheckUtil.getInstance().isCheckResponseAvailable(response)) {

            if (CheckUtil.getInstance().isCheckTopNewsListNotNull(response)) {

                List<NewsBean> list = response.body().getResult().getData();

                if (CheckUtil.getInstance().isCheckListUsable(list)) {
                    for (NewsBean newsBean : list) {
                        insertSingleObject(newsBean);
                    }
                }
            }
        }
    }

}
