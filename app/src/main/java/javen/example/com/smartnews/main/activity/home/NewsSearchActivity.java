package javen.example.com.smartnews.main.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javen.example.com.smartnews.BaseActivity;
import javen.example.com.smartnews.R;
import javen.example.com.smartnews.custom_view.CustomToolBar;
import javen.example.com.smartnews.main.iinterface.home.INewsSearchActivity;
import javen.example.com.smartnews.main.presenter.home.NewsSearchPresenter;
import javen.example.com.smartnews.utils.WindowUtil;

/**
 * Created by Javen on 19/01/2018.
 */

public class NewsSearchActivity extends BaseActivity<NewsSearchPresenter> implements INewsSearchActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.ContentActivityTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.common_layout;
    }

    @Override
    public void initView() {
        WindowUtil.getInstance().setStatusBarTextAndIconDark(NewsSearchActivity.this);
        initCustomToolBar();
    }

    @Override
    public NewsSearchPresenter initPresent() {
        return new NewsSearchPresenter(NewsSearchActivity.this);
    }

    @Override
    public void init() {

    }

    private void initCustomToolBar() {
        CustomToolBar customToolBar = findViewById(R.id.custom_toolbar);
        customToolBar.setNavigationIcon(R.drawable.back_arrow);
        customToolBar.setToolbarType(CustomToolBar.TOOLBAR_SEARCH_ACTIVITY);
        customToolBar.setNavigationOnClickListener(v -> finishCurrentActivity());
    }

    private void finishCurrentActivity() {
        finish();
        overridePendingTransition(0, R.anim.common_right_out);
    }


}
