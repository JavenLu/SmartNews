package javen.example.com.news.main.iinterface.home;

import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.bean.news.NewsChannelBean;
import javen.example.com.news.main.iinterface.RequestCallBack;


/**
 * Created by Javen on 30/11/2017.
 */

public interface INewsChannelModel<T> {

    void lodeNewsChannels(RequestCallBack<Map<Integer, List<NewsChannelBean>>> callback);

    void initNewsChannelDB();

    public void insertSingleObject(T object);

    public void upDateSingleObject(T object);

    public List<T> queryAllObjectByNewsChannelSelect();

    public List<T> queryAllObjectByNewsChannelUnSelect();

    public Map<Integer, List<T>> getAllNewsChannelDataReturnMap();

    public int getAllNewsChannelDataSize();

    public List<T> loadNewsChannelsIndexGt(int channelIndex);

    public void upDateDBWhenOnItemClick(NewsChannelBean newsChannelBean, boolean isChannelMine);

    public List<T> loadNewsChannelsLtAndUnSelect(int channelIndex);

    public long getSelectedNewsDataSize();

    public void upDataDBWhenDragFinished(int fromPosition, int toPosition);

    public T getNewsChannelSingleObjectByIndex(int position);

    public List<T> queryAllObjectByBetween(int from, int to);


}

