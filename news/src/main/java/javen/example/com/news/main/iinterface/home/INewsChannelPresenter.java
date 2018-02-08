package javen.example.com.news.main.iinterface.home;


import javen.example.com.commonlibrary.bean.news.NewsChannelBean;

/**
 * Created by Javen on 30/11/2017.
 */

public interface INewsChannelPresenter {
    void loadNewsChannelData();

    void upDateDBWhenOnItemClick(NewsChannelBean newsChannelBean, boolean isChannelMine);

    void upDateDBWhenDragFinished(int fromPosition, int toPosition);
}
