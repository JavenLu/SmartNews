package javen.example.com.news.main.activity.home;

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
    private OnItemClickListener onItemClickListener;

    public HistoryRecyclerViewAdapter(Context context, List<NewsSearchHistoryBean> list) {
        this.context = context;
        this.list = list;
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
        viewHolder.historyName.setOnClickListener(v -> {
            onItemClickListener.onItemClickListener(list.get(position).getNewsKey());
            clearData();
        });
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
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClickListener(String key);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
