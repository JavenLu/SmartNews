package javen.example.com.smartnews.main.iinterface.home;

import javen.example.com.smartnews.db.news_channel.NewsChannelBean;

/**
 * Created by Javen on 30/11/2017.
 */

public interface INewsChannelPresenter {
    void loadNewsChannelData();

    void upDateDBWhenOnItemClick(NewsChannelBean newsChannelBean, boolean isChannelMine);
}
