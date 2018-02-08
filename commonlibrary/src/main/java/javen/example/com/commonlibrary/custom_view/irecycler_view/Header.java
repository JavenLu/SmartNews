package javen.example.com.commonlibrary.custom_view.irecycler_view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import javen.example.com.commonlibrary.R;
import javen.example.com.commonlibrary.custom_view.header_view.HeaderNewsView;


/**
 * Created by Javen on 26/12/2017.
 */

public class Header extends FrameLayout implements RefreshTrigger {

    private TextView headerContentTextView;
    private HeaderNewsView headerNewsView;

    public Header(@NonNull Context context) {
        this(context, null);
    }

    public Header(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Header(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.header_layout, this);
        headerContentTextView = findViewById(R.id.header_content);
        headerNewsView = findViewById(R.id.header_news_view);
        headerNewsView.setViewColor(Color.GRAY);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {
        if (!finished) {
            headerNewsView.setValue((int) (moved * 0.5));
        } else {
            headerNewsView.setValue((int) (moved * 0.5));
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRelease() {
        headerNewsView.startAnim(1000);
    }

    @Override
    public void onComplete() {
        headerNewsView.stopAnim();
    }

    @Override
    public void onReset() {

    }

    @Override
    public void switchReleaseAndSwipeStatus(int status) {
        if (status == IRecyclerView.STATUS_RELEASE_TO_REFRESH) {
            headerContentTextView.setText(R.string.header_up_recommend);
        } else if (status == IRecyclerView.STATUS_SWIPING_TO_REFRESH) {
            headerContentTextView.setText(R.string.header_pull_down_recommend);
        }
    }
}
