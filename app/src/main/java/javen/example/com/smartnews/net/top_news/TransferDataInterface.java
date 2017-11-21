package javen.example.com.smartnews.net.top_news;

import java.util.List;

import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import retrofit2.Response;

/**
 * Created by Javen on 21/11/2017.
 */

public interface TransferDataInterface<E> {
    public void getTopNewsData(Response<TopNewsResultBean> response);
    public void insertTopNewsListIntoDataBase(List<TopNewsBean> list);
}
