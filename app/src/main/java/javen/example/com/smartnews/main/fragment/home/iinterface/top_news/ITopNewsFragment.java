package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;

import java.util.List;

/**
 * Created by Javen on 17/11/2017.
 */

public interface ITopNewsFragment<T extends IDispalyNews> {
    public void getTopNewsData(List<T> list);
}
