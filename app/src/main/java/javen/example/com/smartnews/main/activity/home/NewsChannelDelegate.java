package javen.example.com.smartnews.main.activity.home;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.delegate.AdapterDelegate;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.IDispalyNews;

/**
 * Created by Javen on 01/12/2017.
 */

public class NewsChannelDelegate extends AdapterDelegate<List<IDispalyNews>> {
    private LayoutInflater inflater;
    private Context context;

    public NewsChannelDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
        context = activity;
    }

    @Override
    protected boolean isForViewType(@NonNull List<IDispalyNews> items, int position) {
        return items.get(position) instanceof NewsChannelBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.news_channel_recycler_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<IDispalyNews> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        NewsChannelBean newsChannelBean = (NewsChannelBean) items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(newsChannelBean.getNewsChannelName());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.news_channel_Text_view);
        }
    }

}
