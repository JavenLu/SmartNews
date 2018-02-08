package javen.example.com.commonlibrary.custom_view.search_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javen.example.com.commonlibrary.R;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;

/**
 * Created by Javen on 31/01/2018.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<NewsSearchHistoryBean> list;

    public HistoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.historyName.setText(list.get(position).getNewsKey());
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView historyName;

        public ViewHolder(View itemView) {
            super(itemView);
            historyName = itemView.findViewById(R.id.history_name);
        }
    }

    public void setData(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }
}
