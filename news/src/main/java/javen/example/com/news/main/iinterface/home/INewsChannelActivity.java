package javen.example.com.news.main.iinterface.home;

import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.bean.news.NewsChannelBean;


/**
 * Created by Javen on 30/11/2017.
 */

public interface INewsChannelActivity {
    void getNewsChannelDataSuccess(Map<Integer, List<NewsChannelBean>> data);

    void getNewsChannelDataFail(String errorMsg);

    void updateNewsChannelDataWhenDragFinished(int fromPosition, int toPosition);
}
