package javen.example.com.smartnews.main.activity.home;

import android.app.Activity;

import java.util.List;

import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.delegate.CommonRecyclerViewAdapter;
import javen.example.com.smartnews.main.iinterface.home.INewsChannelActivity;

/**
 * Created by Javen on 04/12/2017.
 */

public class NewsChannelRecyclerViewAdapter extends CommonRecyclerViewAdapter {
    private Activity activity;

    public NewsChannelRecyclerViewAdapter(Activity activity, List<NewsChannelBean> itemList) {
        super(activity, itemList);
        this.activity = activity;
    }

    public void add(int position, NewsChannelBean item) {
        getData().add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    private void refreshItemRangeGtCurrentPosition(int position) {
        if (position != getDataListSize()) {
            notifyItemRangeChanged(position, getDataListSize() - position);
        }
    }

    private int getDataListSize() {
        return getItemCount();
    }


    public void addMore(List<NewsChannelBean> data) {
        int startPosition = getData().size();
        getData().addAll(data);
        notifyItemRangeInserted(startPosition, getData().size());
    }

    public void delete(int position) {

        getData().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());

    }

}
