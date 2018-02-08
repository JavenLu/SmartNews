package javen.example.com.commonlibrary.net;

import retrofit2.Response;

/**
 * Created by Javen on 21/11/2017.
 */

public interface TransferDataInterface<E> {
    public void getTopNewsData(Response<NewsResultBean> response);
}
