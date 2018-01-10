package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;

import java.util.List;

import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;
import javen.example.com.smartnews.net.top_news.NewsResultBean;
import retrofit2.Response;

/**
 * Created by Javen on 17/11/2017.
 */

public interface INewsModel<T> {

    public void insertSingleObject(T object);

    public void deleteSingleObject(T object);

    public void deleteAllData();

    public void updateSingleObject(T object);

    public List<T> queryAllObject();

    public List<T> queryAllObjectByType(String type);

    public void requestNewsDataFromServer(INewsFragment<NewsBean> iNewsFragment, String type, String chineseNewsType);

    public void storeDataAndGetIt(INewsFragment<NewsBean> iNewsFragment, Response<NewsResultBean> response, String chineseNewsType);

}
