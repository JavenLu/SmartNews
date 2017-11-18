package javen.example.com.smartnews.main.fragment.a_little_video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.a_little_video.iinterface.IALittleVideoFragment;
import javen.example.com.smartnews.main.fragment.a_little_video.presenter.ALittleVideoFragmentPresenter;

/**
 * Created by Javen on 10/11/2017.
 */

public class ALittleVideoFragment extends BaseFragment<BaseFragmentPresenter> implements IALittleVideoFragment {

    @Override
    public View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContext().getResources().getLayout(R.layout.a_little_video_fragment_layout), container, false);
    }

    @Override
    public BaseFragmentPresenter initPresent() {
        return new ALittleVideoFragmentPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {

    }
}
