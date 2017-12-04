package javen.example.com.smartnews.main.iinterface.home;

import java.util.List;
import java.util.Map;

import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.iinterface.RequestCallBack;

/**
 * Created by Javen on 30/11/2017.
 */

public interface INewsChannelModel<T> {

    void lodeNewsChannels(RequestCallBack<Map<Integer, List<NewsChannelBean>>> callback);

    void initNewsChannelDB();

    public void insertSingleObject(T object);

    public List<T> queryAllObjectByNewsChannelSelect();

    public List<T> queryAllObjectByNewsChannelUnSelect();

    public Map<Integer, List<T>> getAllNewsChannelData();


}

