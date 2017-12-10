package javen.example.com.smartnews.main.activity.home;

import android.app.Activity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.delegate.CommonRecyclerViewAdapter;
import javen.example.com.smartnews.main.iinterface.home.INewsChannelActivity;
import javen.example.com.smartnews.utils.ItemDragHelperCallback;

/**
 * Created by Javen on 04/12/2017.
 */

public class NewsChannelRecyclerViewAdapter extends CommonRecyclerViewAdapter implements ItemDragHelperCallback.OnItemMoveListener {
    private Activity activity;
    private INewsChannelActivity iNewsChannelActivity;

    public NewsChannelRecyclerViewAdapter(Activity activity, List<NewsChannelBean> itemList) {
        super(activity, itemList);
        this.activity = activity;
        this.iNewsChannelActivity = (INewsChannelActivity) activity;
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {

        if (isCheckFixed(fromPosition, toPosition, getData())) {
            return false;
        }

        Collections.swap(getData(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        iNewsChannelActivity.updateNewsChannelDataWhenDragFinished(fromPosition,toPosition);
        return true;
    }

    private boolean isCheckFixed(int fromPosition, int toPosition, List<NewsChannelBean> data) {
        return data.get(fromPosition).getNewsChannelFixed() || data.get(toPosition).getNewsChannelFixed();
    }

    public void setLongPressEnable(ItemDragHelperCallback itemDragHelperCallback, int position) {
        List<NewsChannelBean> data = getData();
        NewsChannelBean newsChannelBean = data.get(position);
        boolean isFixed = newsChannelBean.getNewsChannelFixed();

        if (isFixed) {
            itemDragHelperCallback.setLongPressEnabled(false);
        } else {
            itemDragHelperCallback.setLongPressEnabled(true);
        }
    }
}
