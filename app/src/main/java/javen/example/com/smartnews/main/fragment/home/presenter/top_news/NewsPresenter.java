package javen.example.com.smartnews.main.fragment.home.presenter.top_news;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
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
import javen.example.com.smartnews.utils.RxJavaTransformerUtil;
import retrofit2.Response;

/**
 * Created by Javen on 17/11/2017.
 */

public class NewsPresenter extends BaseFragmentPresenter<NewsFragment> implements INewsPresenter<NewsBean> {
    private INewsFragment iNewsFragment;
    private INewsModel iNewsModel;

    public NewsPresenter(INewsFragment iNewsFragment) {
        this.iNewsFragment = iNewsFragment;
        this.iNewsModel = new NewsModel(NewsPresenter.this);
    }

    @Override
    public void requestNewsDataFromServer(INewsFragment<NewsBean> iNewsFragment, String type, String chineseNewsType) {
        iNewsModel.requestNewsDataFromServer(iNewsFragment, type,chineseNewsType);
    }

    public List<NewsBean> getNewsFromDataBase(String type) {

        return iNewsModel.queryAllObjectByType(type);
    }

}
