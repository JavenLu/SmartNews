package javen.example.com.smartnews.main.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeFragment;
import javen.example.com.smartnews.main.fragment.home.presenter.HomePresenter;

/**
 * Created by Javen on 10/11/2017.
 */

public class HomeFragment extends BaseFragment<BaseFragmentPresenter> implements IHomeFragment {
    private HomePresenter homePresenter;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    @Override
    public BaseFragmentPresenter initPresent() {
        return new HomePresenter(this);
    }

    @Override
    public void initData() {
        homePresenter = (HomePresenter) baseFragmentPresenter;
        fragmentList = homePresenter.getHomeFragments();

    }

    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContext().getResources().getLayout(R.layout.home_fragment_layout), container, false);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        fragmentManager = getActivity().getSupportFragmentManager();
        viewPager.setAdapter(new FragmentAdapter(fragmentManager));
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
        public int getCount() {
            return fragmentList != null && fragmentList.size() > 0 ? fragmentList.size() : 0;
        }
    }

}
