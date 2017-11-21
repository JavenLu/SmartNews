package javen.example.com.smartnews.main.fragment.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.presenter.top_news.TopNewsPresenter;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsFragment extends BaseFragment<TopNewsPresenter> implements ITopNewsFragment<TopNewsBean> {
    public static final String TAG = TopNewsFragment.class.getSimpleName();
    private RecyclerView topNewsRecyclerView;
    private TopNewsPresenter topNewsPresenter;


    @Override
    public TopNewsPresenter initPresent() {
        return new TopNewsPresenter(this);
    }

    @Override
    public void initData() {
        topNewsPresenter = (TopNewsPresenter) baseFragmentPresenter;
        topNewsPresenter.requestTopNewsDataFromServer();
    }

    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_news_fragment_layout, container, false);
    }

    @Override
    public void initView(View view) {
        topNewsRecyclerView = view.findViewById(R.id.recycler_view);
    }


    @Override
    public void getTopNewsData(List<TopNewsBean> list) {
        topNewsPresenter.insertTopNewsListIntoDataBase(list);
    }

}
