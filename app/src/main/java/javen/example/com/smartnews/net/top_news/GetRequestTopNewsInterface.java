package javen.example.com.smartnews.net.top_news;

import javen.example.com.smartnews.net.NetConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Javen on 21/11/2017.
 */

public interface GetRequestTopNewsInterface {

    @GET(NetConstants.TOP_NEWS_PATH)
    Call<NewsResultBean> getTopNewsResult(@Header("Authorization") String authorization, @Query("type") String type);

}
