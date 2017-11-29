package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;


import java.util.List;

/**
 * Created by Javen on 17/11/2017.
 */

public interface INewsPresenter<T extends IDispalyNews> {
    public void requestTopNewsDataFromServer();

    public void insertTopNewsListIntoDataBase(List<T> list);

    public List<T> getAllTopNewsFromDataBase();

}
