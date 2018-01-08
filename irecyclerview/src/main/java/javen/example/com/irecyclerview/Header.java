package javen.example.com.irecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by Javen on 26/12/2017.
 */

public class Header extends FrameLayout implements RefreshTrigger {

    private TextView headerContentTextView;
    private MaterialProgressBar progressBar;

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
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(GONE);

    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {

    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRelease() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
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
