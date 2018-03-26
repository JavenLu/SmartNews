package javen.example.com.news.main.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javen.example.com.commonlibrary.mvp.activity.view.BaseActivity;
import javen.example.com.commonlibrary.utils.ToastUtil;
import javen.example.com.commonlibrary.utils.WindowUtil;
import javen.example.com.news.R;
import javen.example.com.news.main.activity.home.NewsDetailsActivity;
import javen.example.com.news.main.fragment.home.HomeFragment;
import javen.example.com.news.main.fragment.home.bean.top_news.NewsDelegate;
import javen.example.com.news.main.helper.MainHelper;
import javen.example.com.news.main.iinterface.IMainActivity;
import javen.example.com.news.main.presenter.MainPresenter;

import static javen.example.com.news.main.helper.MainHelper.FIRST_PAGE;
import static javen.example.com.news.main.helper.MainHelper.FOURTH_PAGE;
import static javen.example.com.news.main.helper.MainHelper.SECOND_PAGE;
import static javen.example.com.news.main.helper.MainHelper.THIRD_PAGE;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainActivity, NewsDelegate.OnItemClickListenerInTopNewsDelegate {
    public static final int BACK_FROM_NEWS_CHANNEL_ACTIVITY = 0x10;
    public static final int EXIT_APP_TIME = 2000;

    private BottomNavigationViewEx bottomNavigationViewEx;
    private MainHelper mainHelper;
    private long pressOnKeyDownTime;

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

                int i = item.getItemId();
                if (i == R.id.menu_home) {
                    mainHelper.switchFragmentByClickBottomNavigationView(FIRST_PAGE);

                } else if (i == R.id.menu_second) {
                    mainHelper.switchFragmentByClickBottomNavigationView(SECOND_PAGE);

                } else if (i == R.id.menu_third) {
                    mainHelper.switchFragmentByClickBottomNavigationView(THIRD_PAGE);

                } else if (i == R.id.menu_fourth) {
                    mainHelper.switchFragmentByClickBottomNavigationView(FOURTH_PAGE);

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
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra("webUrl", webContentUrl);
        startActivity(intent);
        overridePendingTransition(R.anim.common_right_in, R.anim.anim_stay);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BACK_FROM_NEWS_CHANNEL_ACTIVITY:
                if (mainHelper.isCheckFirstPageShow()) {
                    HomeFragment homeFragment = (HomeFragment) mainHelper.getFirstPageInstance();
                    homeFragment.refreshUi(resultCode);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long onKeyDownTime = System.currentTimeMillis();

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            exitApp(onKeyDownTime);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private void exitApp(long onKeyDownTime) {
        if (onKeyDownTime - pressOnKeyDownTime > EXIT_APP_TIME) {
            showExitToast();
            pressOnKeyDownTime = System.currentTimeMillis();
        } else {
            deleteAllNewsData();
            finish();
            System.exit(0);
        }
    }

    private void deleteAllNewsData() {
        basePresenter.deleteAllNewsData();
    }

    private void showExitToast() {
        ToastUtil toastUtil = ToastUtil.getInstance(this);
        toastUtil.makeText(getResources().getString(R.string.exit_content), Toast.LENGTH_SHORT);
        toastUtil.show();
    }


}
