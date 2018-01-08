package javen.example.com.smartnews.main.fragment.home.iinterface.top_news;

import java.util.List;

/**
 * Created by Javen on 17/11/2017.
 */

public interface INewsFragment<T extends IDisplayNews> {
    public void getTopNewsData(List<T> list);
}
