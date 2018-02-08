package javen.example.com.news.main.presenter.home;

import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.bean.news.NewsChannelBean;
import javen.example.com.commonlibrary.mvp.activity.presenter.BasePresenter;
import javen.example.com.news.main.activity.home.NewsChannelActivity;
import javen.example.com.news.main.iinterface.RequestCallBack;
import javen.example.com.news.main.iinterface.home.INewsChannelActivity;
import javen.example.com.news.main.iinterface.home.INewsChannelModel;
import javen.example.com.news.main.iinterface.home.INewsChannelPresenter;
import javen.example.com.news.main.model.home.NewsChannelModel;


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

    @Override
    public void upDateDBWhenOnItemClick(NewsChannelBean newsChannelBean, boolean isChannelMine) {
        iNewsChannelModel.upDateDBWhenOnItemClick(newsChannelBean, isChannelMine);
    }

    @Override
    public void upDateDBWhenDragFinished(int fromPosition, int toPosition) {
        iNewsChannelModel.upDataDBWhenDragFinished(fromPosition,toPosition);
    }


}
