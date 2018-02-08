package javen.example.com.news.main.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.custom_view.CustomToolBar;
import javen.example.com.commonlibrary.custom_view.decoration.DividerDecoration;
import javen.example.com.commonlibrary.custom_view.search_view.SearchView;
import javen.example.com.commonlibrary.mvp.activity.view.BaseActivity;
import javen.example.com.commonlibrary.utils.WindowUtil;
import javen.example.com.news.R;
import javen.example.com.news.main.delegate.CommonRecyclerViewAdapter;
import javen.example.com.news.main.fragment.home.bean.top_news.NewsDelegate;
import javen.example.com.news.main.iinterface.home.INewsSearchActivity;
import javen.example.com.news.main.presenter.home.NewsSearchPresenter;
/**
 * Created by Javen on 19/01/2018.
 */

public class NewsSearchActivity extends BaseActivity<NewsSearchPresenter> implements INewsSearchActivity, SearchView.SearchHistoryKey, SearchView.AccurateSearchHistoryKey,NewsDelegate.OnItemClickListenerInTopNewsDelegate {
    private CustomToolBar customToolBar;
    private List<NewsSearchHistoryBean> historyList;
    private List<NewsBean> newsList;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter commonRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.ContentActivityTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.news_search_layout;
    }

    @Override
    public void initView() {
        WindowUtil.getInstance().setStatusBarTextAndIconDark(NewsSearchActivity.this);
        initCustomToolBar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewsSearchActivity.this));
        recyclerView.addItemDecoration(new DividerDecoration(NewsSearchActivity.this, R.dimen.x1, R.dimen.x16, DividerDecoration.BOTTOM_LINE_TYPE));
        commonRecyclerViewAdapter = new CommonRecyclerViewAdapter(NewsSearchActivity.this, newsList == null ? new ArrayList<NewsBean>() : newsList);
        recyclerView.setAdapter(commonRecyclerViewAdapter);
    }

    @Override
    public NewsSearchPresenter initPresent() {
        return new NewsSearchPresenter(NewsSearchActivity.this);
    }

    @Override
    public void init() {
    }

    private void initCustomToolBar() {
        customToolBar = findViewById(R.id.custom_toolbar);
        customToolBar.setNavigationIcon(R.drawable.back_arrow);
        customToolBar.setToolbarType(CustomToolBar.TOOLBAR_SEARCH_ACTIVITY);
        customToolBar.setNavigationOnClickListener(v -> finishCurrentActivity());
    }

    private void finishCurrentActivity() {
        finish();
        overridePendingTransition(0, R.anim.common_right_out);
    }


    @Override
    public void doSearchHistoryKey(String key) {
        historyList = basePresenter.getHistorySearchKeyFromDataBase(key);
        customToolBar.setHistoryKeyData(historyList);
    }

    @Override
    public void doSearchNews(String key) {
        newsList = basePresenter.getNewsListBySearchNewsDataBase(key);
        commonRecyclerViewAdapter.setDataAndRefresh(newsList);
        basePresenter.insertSearchKeyToDataBase(key);
    }

    /**
     * RecyclerView ItemClickListener
     * @param webContentUrl
     */
    @Override
    public void onItemClick(String webContentUrl) {

    }
}
