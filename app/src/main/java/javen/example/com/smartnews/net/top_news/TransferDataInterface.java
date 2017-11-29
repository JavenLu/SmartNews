package javen.example.com.smartnews.net.top_news;

import retrofit2.Response;

/**
 * Created by Javen on 21/11/2017.
 */

public interface TransferDataInterface<E> {
    public void getTopNewsData(Response<NewsResultBean> response);
}
