package javen.example.com.news.main.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsChannelBean;
import javen.example.com.commonlibrary.custom_view.CustomToolBar;
import javen.example.com.commonlibrary.mvp.fragment.presenter.BaseFragmentPresenter;
import javen.example.com.commonlibrary.mvp.fragment.view.BaseFragment;
import javen.example.com.commonlibrary.utils.CheckUtil;
import javen.example.com.commonlibrary.utils.CommonUiUtil;
import javen.example.com.news.R;
import javen.example.com.news.db.DBConstant;
import javen.example.com.news.main.activity.home.NewsChannelActivity;
import javen.example.com.news.main.activity.home.NewsSearchActivity;
import javen.example.com.news.main.fragment.home.iinterface.IHomeFragment;
import javen.example.com.news.main.fragment.home.model.HomeModel;
import javen.example.com.news.main.fragment.home.presenter.HomePresenter;
import javen.example.com.news.main.iinterface.home.INewsChannelActivity;
import javen.example.com.news.main.presenter.home.NewsChannelPresenter;


/**
 * Created by Javen on 10/11/2017.
 */

public class HomeFragment extends BaseFragment<BaseFragmentPresenter> implements IHomeFragment, INewsChannelActivity {
    public static final int REQUEST_CODE = 0x10;

    private HomePresenter homePresenter;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private ImageView channelImageView;
    private NewsChannelPresenter newsChannelPresenter;
    private FragmentAdapter fragmentAdapter;
    private List<String> titleList;
    private TabLayout tabLayout;
    private String currentTitleByChangePage;


    @Override
    public BaseFragmentPresenter initPresent() {
        return new HomePresenter(this);
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        homePresenter = (HomePresenter) baseFragmentPresenter;
        newsChannelPresenter = new NewsChannelPresenter(HomeFragment.this);
    }

    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContext().getResources().getLayout(R.layout.home_fragment_layout), container, false);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        channelImageView = view.findViewById(R.id.add_channel_image_view);
        tabLayout = view.findViewById(R.id.slide_tab_layout);
        initToolBar(view);
        viewPager = view.findViewById(R.id.viewPager);
        fragmentManager = getChildFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        CommonUiUtil.getInstance().dynamicSetTabLayoutMode(tabLayout);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentTitleByChangePage = titleList.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initClickListener();

    }

    private void initToolBar(View view) {
        CustomToolBar customToolBar = view.findViewById(R.id.custom_toolbar);
        customToolBar.setToolbarType(CustomToolBar.TOOLBAR_HOME_ACTIVITY);
        customToolBar.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
        customToolBar.setOnClickListenerOnHomeFragment(this::goToNewsSearchActivity);
    }

    private void initClickListener() {
        channelImageView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NewsChannelActivity.class);
            getActivity().startActivityForResult(intent, REQUEST_CODE);
            getActivity().overridePendingTransition(R.anim.alpha_activity_in, R.anim.anim_stay);
        });
    }

    @Override
    public void getNewsChannelDataSuccess(Map<Integer, List<NewsChannelBean>> data) {
        if (fragmentList != null && fragmentAdapter != null) {
            List<NewsChannelBean> list = data.get(DBConstant.NEWS_CHANNEL_MINE);
            setCurrentTitleName(list);
            Map<String, List> map = homePresenter.getHomeFragments(list);

            if (map != null && map.size() > 0) {
                initDataAndRefreshAdapter(map);
                CommonUiUtil.getInstance().dynamicSetTabLayoutMode(tabLayout);
                int currentViewPagerPosition = getCurrentViewPagerPosition();
                setViewPagerCurrentItem(currentViewPagerPosition);
            }


        }
    }

    private void setViewPagerCurrentItem(int currentViewPagerPosition) {
        if (CommonLibraryApplication.isEnterApp) {
            CommonLibraryApplication.isEnterApp = false;
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(currentViewPagerPosition);
        }
    }

    private void setCurrentTitleName(List<NewsChannelBean> list) {
        if (CheckUtil.getInstance().isCheckListUsable(list)) {
            NewsChannelBean newsChannelBean = list.get(list.size() - 1);
            currentTitleByChangePage = newsChannelBean.getNewsChannelName();
        }
    }

    private int getCurrentViewPagerPosition() {
        int currentViewPagerPosition = 0;

        for (int i = 0; i < titleList.size(); i++) {
            if (!TextUtils.isEmpty(currentTitleByChangePage)) {
                if (currentTitleByChangePage.equals(titleList.get(i))) {
                    currentViewPagerPosition = i;
                    return currentViewPagerPosition;
                }
            }
        }

        return currentViewPagerPosition;
    }

    private void initDataAndRefreshAdapter(Map<String, List> map) {
        titleList = map.get(HomeModel.CHANNEL_NAME_LIST);
        fragmentList = map.get(HomeModel.FRAGMENT_LIST);
        fragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void getNewsChannelDataFail(String errorMsg) {

    }

    @Override
    public void updateNewsChannelDataWhenDragFinished(int fromPosition, int toPosition) {

    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList != null && fragmentList.size() > 0 ? fragmentList.size() : 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    }

    public void refreshUi(int resultCode) {
        if (resultCode == 0 && CommonLibraryApplication.isChannelChange) {
            newsChannelPresenter.loadNewsChannelData();
            CommonLibraryApplication.isChannelChange = false;
        }
    }

    public void goToNewsSearchActivity() {
        Intent intent = new Intent(getActivity(), NewsSearchActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.common_right_in, R.anim.anim_stay);
    }
}
