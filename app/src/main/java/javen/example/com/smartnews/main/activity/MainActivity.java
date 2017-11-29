package javen.example.com.smartnews.main.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javen.example.com.smartnews.BaseActivity;
import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.NewsDelegate;
import javen.example.com.smartnews.main.helper.MainHelper;
import javen.example.com.smartnews.main.iinterface.IMainActivity;
import javen.example.com.smartnews.main.presenter.MainPresenter;
import javen.example.com.smartnews.utils.WindowUtil;

import static javen.example.com.smartnews.main.helper.MainHelper.FIRST_PAGE;
import static javen.example.com.smartnews.main.helper.MainHelper.FOURTH_PAGE;
import static javen.example.com.smartnews.main.helper.MainHelper.SECOND_PAGE;
import static javen.example.com.smartnews.main.helper.MainHelper.THIRD_PAGE;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainActivity, NewsDelegate.OnItemClickListenerInTopNewsDelegate {
    private BottomNavigationViewEx bottomNavigationViewEx;
    private MainHelper mainHelper;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        WindowUtil.getInstance().setStatusBar(MainActivity.this);
        initBottomNavigationView();
        mainHelper.switchFragmentByClickBottomNavigationView(FIRST_PAGE);
    }

    private void initBottomNavigationView() {

        bottomNavigationViewEx = findViewById(R.id.navigation_view);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        mainHelper.switchFragmentByClickBottomNavigationView(FIRST_PAGE);
                        break;
                    case R.id.menu_second:
                        mainHelper.switchFragmentByClickBottomNavigationView(SECOND_PAGE);
                        break;
                    case R.id.menu_third:
                        mainHelper.switchFragmentByClickBottomNavigationView(THIRD_PAGE);
                        break;
                    case R.id.menu_fourth:
                        mainHelper.switchFragmentByClickBottomNavigationView(FOURTH_PAGE);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public MainPresenter initPresent() {
        return new MainPresenter(this);
    }

    @Override
    public void init() {
        mainHelper = MainHelper.getInstance(this);
    }


    @Override
    public void onItemClick(String webContentUrl) {
        Intent intent = new Intent(this, CommonContentActivity.class);
        intent.putExtra("webUrl", webContentUrl);
        startActivity(intent);
        overridePendingTransition(R.anim.common_right_in, R.anim.anim_stay);
    }

}
