package javen.example.com.smartnews.main.activity.home;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import javen.example.com.smartnews.BaseActivity;
import javen.example.com.smartnews.MyApplication;
import javen.example.com.smartnews.R;
import javen.example.com.smartnews.custom_view.CustomToolBar;
import javen.example.com.smartnews.custom_view.FlexibleRecyclerView;
import javen.example.com.smartnews.db.DBConstant;
import javen.example.com.smartnews.db.news_channel.NewsChannelBean;
import javen.example.com.smartnews.main.helper.LayoutManagerHelper;
import javen.example.com.smartnews.main.iinterface.home.INewsChannelActivity;
import javen.example.com.smartnews.main.presenter.home.NewsChannelPresenter;
import javen.example.com.smartnews.utils.ItemDragHelperCallback;
import javen.example.com.smartnews.utils.WindowUtil;

/**
 * Created by Javen on 30/11/2017.
 */

public class NewsChannelActivity extends BaseActivity<NewsChannelPresenter> implements INewsChannelActivity {
    private NewsChannelPresenter newsChannelPresenter;
    private FlexibleRecyclerView mineRecyclerView;
    private FlexibleRecyclerView moreRecyclerView;
    private NewsChannelRecyclerViewAdapter newsMineAdapter, newsMoreAdapter;

    @Override
    public int getLayout() {
        return R.layout.news_channel_layout;
    }

    @Override
    public void initView() {
        WindowUtil.getInstance().setStatusBar(NewsChannelActivity.this);
        initCustomToolBar();
        mineRecyclerView = findViewById(R.id.news_channel_mine_recycler_view);
        moreRecyclerView = findViewById(R.id.news_channel_more_recycler_view);
    }

    @Override
    public NewsChannelPresenter initPresent() {
        return new NewsChannelPresenter(NewsChannelActivity.this);
    }

    @Override
    public void init() {
        newsChannelPresenter = basePresenter;
    }

    private void finishCurrentActivity() {
        finish();
        overridePendingTransition(0, R.anim.alpha_activity_out);
        setResult(0);
    }

    private void initCustomToolBar() {
        CustomToolBar customToolBar = findViewById(R.id.custom_toolbar);
        customToolBar.setNavigationIcon(R.drawable.back_arrow);
        customToolBar.setToolbarType(CustomToolBar.TOOLBAR_SECOND_LEVEL);
        customToolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        customToolBar.setNavigationOnClickListener(v -> finishCurrentActivity());

        customToolBar.setTitle(getString(R.string.channel_manage));
        customToolBar.setTitleSize(getResources().getDimensionPixelSize(R.dimen.x10));
        customToolBar.setTextColor(getResources().getColor(R.color.white));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finishCurrentActivity();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void getNewsChannelDataSuccess(Map<Integer, List<NewsChannelBean>> data) {
        List<NewsChannelBean> newsChannelMine = data.get(DBConstant.NEWS_CHANNEL_MINE);
        List<NewsChannelBean> newsChannelUnSelect = data.get(DBConstant.NEWS_CHANNEL_MORE);

        initRecyclerView(mineRecyclerView, newsChannelMine, true);
        initRecyclerView(moreRecyclerView, newsChannelUnSelect, false);
    }

    @Override
    public void getNewsChannelDataFail(String errorMsg) {
        Toast.makeText(NewsChannelActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateNewsChannelDataWhenDragFinished(int fromPosition, int toPosition) {
        newsChannelPresenter.upDateDBWhenDragFinished(fromPosition, toPosition);
    }

    private void initRecyclerView(RecyclerView recyclerView, List<NewsChannelBean> data, final boolean isChannelMine) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (isChannelMine) {
            RecyclerView.LayoutManager newsMineManager = mineRecyclerView.createLayoutManager(LayoutManagerHelper.GRID_TYPE, 4, LinearLayout.VERTICAL, false);
            newsMineAdapter = new NewsChannelRecyclerViewAdapter(NewsChannelActivity.this, data);
            newsMineAdapter.newsChannelDelegate.setOnItemClickListener(position -> LogicByClickSelectedTag(data, position));

            recyclerView.setLayoutManager(newsMineManager);
            recyclerView.setAdapter(newsMineAdapter);

            initItemDragHelper();
        } else {
            RecyclerView.LayoutManager newsMoreLayoutManager = moreRecyclerView.createLayoutManager(LayoutManagerHelper.GRID_TYPE, 4, LinearLayout.VERTICAL, false);
            newsMoreAdapter = new NewsChannelRecyclerViewAdapter(NewsChannelActivity.this, data);
            newsMoreAdapter.newsChannelDelegate.setOnItemClickListener(position -> LogicByClickUnSelectTag(position, data));

            recyclerView.setLayoutManager(newsMoreLayoutManager);
            recyclerView.setAdapter(newsMoreAdapter);
        }

    }

    private void initItemDragHelper() {
        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback(newsMineAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
        itemTouchHelper.attachToRecyclerView(mineRecyclerView);

        newsMineAdapter.newsChannelDelegate.setOnLongPressClickListener(position -> {
            newsMineAdapter.setLongPressEnable(itemDragHelperCallback, position);
        });
    }

    private void LogicByClickUnSelectTag(int position, List<NewsChannelBean> data) {
        if (position != -1) {
            NewsChannelBean newsChannelBean = data.get(position);
            boolean isNewsChannelFixed = newsChannelBean.getNewsChannelFixed();

            if (!isNewsChannelFixed) {
                MyApplication.isChannelChange = true;
                newsMineAdapter.add(newsMineAdapter.getItemCount(), newsChannelBean);
                newsMoreAdapter.delete(position);
                newsChannelPresenter.upDateDBWhenOnItemClick(newsChannelBean, false);
            }
        }
    }

    private void LogicByClickSelectedTag(List<NewsChannelBean> data, int position) {
        if (position != -1) {
            NewsChannelBean newsChannelBean = data.get(position);
            boolean isNewsChannelFixed = newsChannelBean.getNewsChannelFixed();

            if (!isNewsChannelFixed) {
                MyApplication.isChannelChange = true;
                newsMoreAdapter.add(newsMoreAdapter.getItemCount(), newsChannelBean);
                newsMineAdapter.delete(position);
                newsChannelPresenter.upDateDBWhenOnItemClick(newsChannelBean, true);
            }
        }
    }


}
