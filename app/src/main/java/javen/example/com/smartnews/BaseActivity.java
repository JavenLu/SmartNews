package javen.example.com.smartnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Javen on 10/11/2017.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        basePresenter = initPresent();
        init();
        initView();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract T initPresent();

    public abstract void init();
}
