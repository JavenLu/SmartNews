package javen.example.com.smartnews.main.delegate;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsDelegate;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.IDispalyNews;

/**
 * Created by Javen on 22/11/2017.
 */

public class CommonRecyclerViewAdapter<T extends List<IDispalyNews>> extends RecyclerView.Adapter {
    private T itemList;
    private AdapterDelegatesManager<T> adapterDelegatesManager;

    public CommonRecyclerViewAdapter(Activity activity, T itemList) {
        this.itemList = itemList;
        adapterDelegatesManager = new AdapterDelegatesManager<>();
        adapterDelegatesManager.addDelegate((AdapterDelegate<T>) new TopNewsDelegate(activity));
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegatesManager.getItemViewType(itemList, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterDelegatesManager.onBindViewHolder(itemList, position, holder);
    }

    @Override
    public int getItemCount() {
        return itemList != null && itemList.size() > 0 ? itemList.size() : 0;
    }
}
