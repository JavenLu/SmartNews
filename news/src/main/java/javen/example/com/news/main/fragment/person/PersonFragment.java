package javen.example.com.news.main.fragment.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.commonlibrary.mvp.fragment.presenter.BaseFragmentPresenter;
import javen.example.com.commonlibrary.mvp.fragment.view.BaseFragment;
import javen.example.com.news.R;


/**
 * Created by Javen on 10/11/2017.
 */

public class PersonFragment extends BaseFragment<BaseFragmentPresenter> {

    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContext().getResources().getLayout(R.layout.person_fragment_layout), container, false);
    }

    @Override
    public BaseFragmentPresenter initPresent() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {

    }
}
