package javen.example.com.smartnews.main.fragment.home.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javen.example.com.irecyclerview.IRecyclerView;
import javen.example.com.irecyclerview.OnRefreshListener;
import javen.example.com.irecyclerview.RefreshTrigger;
import javen.example.com.irecyclerview.WrapperAdapter;
import javen.example.com.smartnews.R;
import javen.example.com.smartnews.custom_view.FlexibleRecyclerView;
import javen.example.com.smartnews.main.decoration.DividerDecoration;
import javen.example.com.smartnews.main.helper.LayoutManagerHelper;
import javen.example.com.smartnews.main.delegate.CommonRecyclerViewAdapter;
import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsBean;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.INewsFragment;
import javen.example.com.smartnews.main.fragment.home.presenter.top_news.NewsPresenter;
import javen.example.com.smartnews.utils.CheckUtil;

/**
 * Created by Javen on 17/11/2017.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsFragment<NewsBean>, OnRefreshListener {
    public static final String TAG = NewsFragment.class.getSimpleName();
    private IRecyclerView topNewsRecyclerView;
    private String type, chineseNewsType;
    private List<NewsBean> list;
    private CommonRecyclerViewAdapter commonAdapter;
    private Handler handler = null;

    @Override
    public NewsPresenter initPresent() {
        return new NewsPresenter(this);
    }

    @Override
    public void initData() {
        handler = new Handler();
        Bundle bundle = getArguments();

        if (bundle != null) {
            type = bundle.getString("newsType");
            chineseNewsType = bundle.getString("chineseNewsType");


            if (isCheckTypeNotNull()) {
                list = baseFragmentPresenter.getNewsFromDataBase(chineseNewsType);
                baseFragmentPresenter.requestNewsDataFromServer(this, type, chineseNewsType);
            }
        }

    }

    private boolean isCheckTypeNotNull() {
        return !TextUtils.isEmpty(type) && !TextUtils.isEmpty(chineseNewsType);
    }


    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_news_fragment_layout, container, false);
    }

    @Override
    public void initView(View view) {
        topNewsRecyclerView = view.findViewById(R.id.recycler_view);
        initRecyclerView();
    }


    /**
     * 网络请求结果回调
     *
     * @param list
     */
    @Override
    public void getTopNewsData(List<NewsBean> list) {
        if (CheckUtil.getInstance().isCheckListUsable(list)) {
            this.list = list;
            commonAdapter.setDataAndRefresh(this.list);
            topNewsRecyclerView.setRefreshing(false);
        }
    }

    private void initRecyclerView() {
        topNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topNewsRecyclerView.addItemDecoration(new DividerDecoration(getActivity(), R.dimen.x1, R.dimen.x16, DividerDecoration.BOTTOM_LINE_TYPE));
        commonAdapter = new CommonRecyclerViewAdapter(getActivity(), list);
        topNewsRecyclerView.setIAdapter(commonAdapter);
        topNewsRecyclerView.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onRefresh() {
        baseFragmentPresenter.requestNewsDataFromServer(this, type, chineseNewsType);
    }
}
