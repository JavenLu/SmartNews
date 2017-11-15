package javen.example.com.smartnews.main.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeFragment;
import javen.example.com.smartnews.main.fragment.home.presenter.HomePresenter;

/**
 * Created by Javen on 10/11/2017.
 */

public class HomeFragment extends BaseFragment<BaseFragmentPresenter> implements IHomeFragment{

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContext().getResources().getLayout(R.layout.home_fragment_layout), container, false);
    }

    @Override
    public BaseFragmentPresenter initPresent() {
        return new HomePresenter(this);
    }

}
