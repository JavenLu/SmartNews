package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;

import java.util.List;

/**
 * Created by Javen on 17/11/2017.
 */

public interface ITopNewsModel<T> {

    public void insertSingleObject(T object);

    public void deleteSingleObject(T object);

    public void updateSingleObject(T object);

    public List<T> queryAllObject();

    public List<T> queryAllObjectByType(String type);

}
