package javen.example.com.news.main.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.spinytech.macore.MaAction;
import com.spinytech.macore.MaActionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javen.example.com.commonlibrary.Constant;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.custom_view.CustomToolBar;
import javen.example.com.commonlibrary.custom_view.decoration.DividerDecoration;
import javen.example.com.commonlibrary.mvp.activity.view.BaseActivity;
import javen.example.com.commonlibrary.utils.WindowUtil;
import javen.example.com.news.R;
import javen.example.com.news.main.iinterface.home.INewsSearchActivity;
import javen.example.com.news.main.model.home.SearchHistoryModel;
import javen.example.com.news.main.presenter.home.NewsSearchPresenter;

/**
 * Created by Javen on 19/01/2018.
 */

public class NewsSearchActivity extends BaseActivity<NewsSearchPresenter> implements INewsSearchActivity, HistoryRecyclerViewAdapter.OnItemClickListener {
    private CustomToolBar customToolBar;
    private List<NewsSearchHistoryBean> historyList;
    private RecyclerView recyclerView;
    private static HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

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
        historyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(NewsSearchActivity.this, historyList);
        historyRecyclerViewAdapter.setOnItemClickListener(NewsSearchActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewsSearchActivity.this));
        recyclerView.addItemDecoration(new DividerDecoration(NewsSearchActivity.this, R.dimen.x1, R.dimen.x16, DividerDecoration.BOTTOM_LINE_TYPE));
        recyclerView.setAdapter(historyRecyclerViewAdapter);
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
    public void onItemClickListener(String key) {
        customToolBar.setEditTextContent(key);
    }

    public static class ActionHistoryLikeQuery extends MaAction {
        @Override
        public boolean isAsync(Context context, HashMap<String, String> requestData) {
            return false;
        }

        @Override
        public MaActionResult invoke(Context context, HashMap<String, String> requestData) {
            if (requestData != null) {
                String historyKey = requestData.get(Constant.HISTORY_KEY_NAME);

                if (!TextUtils.isEmpty(historyKey)) {
                    List<NewsSearchHistoryBean> list = SearchHistoryModel.queryHistoryLikeKey(historyKey);
                    historyRecyclerViewAdapter.setData(list);
                    return new MaActionResult.Builder().build();
                }
            }
            return null;

        }
    }

    public static class ActionClearHistoryKey extends MaAction {
        @Override
        public boolean isAsync(Context context, HashMap<String, String> requestData) {
            return false;
        }

        @Override
        public MaActionResult invoke(Context context, HashMap<String, String> requestData) {
            historyRecyclerViewAdapter.clearData();

            return new MaActionResult.Builder().build();
        }
    }
}
