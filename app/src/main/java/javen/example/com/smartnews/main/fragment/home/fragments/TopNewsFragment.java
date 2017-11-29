package javen.example.com.smartnews.main.fragment.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.custom_view.FlexibleRecyclerView;
import javen.example.com.smartnews.main.decoration.DividerDecoration;
import javen.example.com.smartnews.main.helper.LayoutManagerHelper;
import javen.example.com.smartnews.main.delegate.CommonRecyclerViewAdapter;
import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsFragment;
import javen.example.com.smartnews.main.fragment.home.presenter.top_news.NewsPresenter;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsFragment extends BaseFragment<NewsPresenter> implements ITopNewsFragment<NewsBean> {
    public static final String TAG = TopNewsFragment.class.getSimpleName();
    private FlexibleRecyclerView topNewsRecyclerView;

    @Override
    public NewsPresenter initPresent() {
        return new NewsPresenter(this);
    }

    @Override
    public void initData() {
        baseFragmentPresenter.requestTopNewsDataFromServer();
    }

    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_news_fragment_layout, container, false);
    }

    @Override
    public void initView(View view) {
        topNewsRecyclerView = view.findViewById(R.id.recycler_view);
    }

    /**
     * 网络请求结果回调
     *
     * @param list
     */
    @Override
    public void getTopNewsData(List<NewsBean> list) {

        baseFragmentPresenter.insertTopNewsListIntoDataBase(list);
        List<NewsBean> topNewsList = baseFragmentPresenter.getAllTopNewsFromDataBase();

        initRecyclerView(topNewsList);
    }

    private void initRecyclerView(List<NewsBean> topNewsList) {
        RecyclerView.LayoutManager layoutManager = topNewsRecyclerView.createLinearLayoutManager(LayoutManagerHelper.LINEAR_TYPE);

        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).setSmoothScrollbarEnabled(true);
        }

        topNewsRecyclerView.setHasFixedSize(true);
        topNewsRecyclerView.setLayoutManager(layoutManager);
        topNewsRecyclerView.addItemDecoration(new DividerDecoration(getActivity(), R.dimen.x1, R.dimen.x16, DividerDecoration.BOTTOM_LINE_TYPE));
        CommonRecyclerViewAdapter commonAdapter = new CommonRecyclerViewAdapter(getActivity(), topNewsList);
        topNewsRecyclerView.setAdapter(commonAdapter);
    }

}
