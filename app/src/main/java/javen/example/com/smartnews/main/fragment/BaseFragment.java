package javen.example.com.smartnews.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Javen on 10/11/2017.
 */

public abstract class BaseFragment<E extends BaseFragmentPresenter> extends Fragment {
    public E baseFragmentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseFragmentPresenter = initPresent();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getRootView(inflater, container, savedInstanceState);
        initView(view);
        return view;
    }

    public abstract View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract E initPresent();

    public abstract void initData();

    public abstract void initView(View view);
}
