package javen.example.com.smartnews.main.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

import javen.example.com.smartnews.BaseActivity;
import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.iinterface.IMainActivity;
import javen.example.com.smartnews.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainActivity {
    public static final int FIRST_PAGE = 0;
    public static final int SECOND_PAGE = 1;
    public static final int THIRD_PAGE = 2;
    public static final int FOURTH_PAGE = 3;

    private BottomNavigationViewEx bottomNavigationViewEx;
    private ViewPager viewPager;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initBottomNavigationView();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), basePresenter.getViewPagerData()));
    }

    private void initBottomNavigationView() {
        bottomNavigationViewEx = findViewById(R.id.navigation_view);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        position = FIRST_PAGE;
                        break;
                    case R.id.menu_second:
                        position = SECOND_PAGE;
                        break;
                    case R.id.menu_third:
                        position = THIRD_PAGE;
                        break;
                    case R.id.menu_fourth:
                        position = FOURTH_PAGE;
                        break;
                }

                viewPager.setCurrentItem(position);

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

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {

            return list != null && list.size() > 0 ? list.size() : 0;
        }
    }


}
