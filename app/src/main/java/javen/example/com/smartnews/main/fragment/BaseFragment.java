package javen.example.com.smartnews.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.smartnews.BasePresenter;

/**
 * Created by Javen on 10/11/2017.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    public abstract void onCreate();

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

}
