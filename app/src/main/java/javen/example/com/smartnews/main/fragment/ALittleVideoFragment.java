package javen.example.com.smartnews.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.smartnews.R;

/**
 * Created by Javen on 10/11/2017.
 */

public class ALittleVideoFragment extends BaseFragment {
    @Override
    public void onCreate() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContext().getResources().getLayout(R.layout.a_little_video_fragment_layout), container, false);
    }
}
