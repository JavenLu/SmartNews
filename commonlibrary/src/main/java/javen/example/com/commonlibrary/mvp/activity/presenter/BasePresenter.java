package javen.example.com.commonlibrary.mvp.activity.presenter;

import javen.example.com.commonlibrary.mvp.activity.view.BaseActivity;

/**
 * Created by Javen on 10/11/2017.
 */

public abstract class BasePresenter<T extends BaseActivity> {
    public abstract void initData();
}
