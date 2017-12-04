package javen.example.com.smartnews.main.presenter.home;

import java.util.List;
import java.util.Map;

import javen.example.com.smartnews.BasePresenter;
import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.activity.home.NewsChannelActivity;
import javen.example.com.smartnews.main.iinterface.RequestCallBack;
import javen.example.com.smartnews.main.iinterface.home.INewsChannelActivity;
import javen.example.com.smartnews.main.iinterface.home.INewsChannelModel;
import javen.example.com.smartnews.main.iinterface.home.INewsChannelPresenter;
import javen.example.com.smartnews.main.model.home.NewsChannelModel;

/**
 * Created by Javen on 30/11/2017.
 */

public class NewsChannelPresenter extends BasePresenter<NewsChannelActivity> implements INewsChannelPresenter {
    private INewsChannelActivity iNewsChannelActivity;
    private INewsChannelModel iNewsChannelModel;

    public NewsChannelPresenter(INewsChannelActivity iNewsChannelActivity) {
        this.iNewsChannelActivity = iNewsChannelActivity;
        iNewsChannelModel = new NewsChannelModel();
        initData();
    }

    @Override
    public void initData() {
        loadNewsChannelData();
    }


    @Override
    public void loadNewsChannelData() {
        iNewsChannelModel.lodeNewsChannels(new RequestCallBack<Map<Integer, List<NewsChannelBean>>>() {

            @Override
            public void success(Map<Integer, List<NewsChannelBean>> data) {
                iNewsChannelActivity.getNewsChannelDataSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                iNewsChannelActivity.getNewsChannelDataFail(errorMsg);
            }
        });
    }
}
