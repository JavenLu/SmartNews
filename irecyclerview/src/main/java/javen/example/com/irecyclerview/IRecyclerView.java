package javen.example.com.irecyclerview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Javen on 08/01/2018.
 */

public class IRecyclerView extends RecyclerView {

    public static final String TAG = IRecyclerView.class.getSimpleName();

    private static final int STATUS_DEFAULT = 0;

    protected static final int STATUS_SWIPING_TO_REFRESH = 1;

    protected static final int STATUS_RELEASE_TO_REFRESH = 2;

    private static final int STATUS_REFRESHING = 3;

    private RefreshHeaderLayout mRefreshHeaderContainer;

    private View mRefreshHeaderView;

    private LinearLayout mHeaderViewContainer;

    private int mRefreshFinalMoveOffset;

    private int mActivePointerId = -1;
    private int mLastTouchX = 0;
    private int mLastTouchY = 0;

    private boolean mRefreshEnabled;
    private int mStatus;
    private OnRefreshListener mOnRefreshListener;
    private TextView headerContent;
    private LinearLayout mFooterViewContainer;
    private FrameLayout mLoadMoreFooterContainer;

    private boolean isScrolling;

    private ValueAnimator mScrollAnimator;

    public IRecyclerView(Context context) {
        this(context, null);
    }

    public IRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IRecyclerView, defStyle, 0);
        @LayoutRes int refreshHeaderLayoutRes = -1;
        int refreshFinalMoveOffset = -1;
        boolean refreshEnabled;

        try {
            refreshEnabled = typedArray.getBoolean(R.styleable.IRecyclerView_refreshEnabled, false);
            refreshHeaderLayoutRes = typedArray.getResourceId(R.styleable.IRecyclerView_refreshHeaderLayout, -1);
            refreshFinalMoveOffset = typedArray.getDimensionPixelOffset(R.styleable.IRecyclerView_refreshFinalMoveOffset, -1);
        } finally {
            typedArray.recycle();
        }

        initView(refreshHeaderLayoutRes, refreshFinalMoveOffset, refreshEnabled);

    }

    public void setIAdapter(Adapter adapter) {
        ensureRefreshHeaderContainer();
        ensureHeaderViewContainer();
        setAdapter(new WrapperAdapter(adapter, mRefreshHeaderContainer, mHeaderViewContainer, mFooterViewContainer, mLoadMoreFooterContainer));
    }

    private void ensureHeaderViewContainer() {
        if (mHeaderViewContainer == null) {
            mHeaderViewContainer = new LinearLayout(getContext());
            mHeaderViewContainer.setOrientation(LinearLayout.VERTICAL);
            mHeaderViewContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void initView(int refreshHeaderLayoutRes, int refreshFinalMoveOffset, boolean refreshEnabled) {
        setRefreshEnabled(refreshEnabled);

        if (refreshHeaderLayoutRes != -1) {
            setRefreshHeaderView(refreshHeaderLayoutRes);
        }

        if (refreshFinalMoveOffset != -1) {
            setRefreshFinalMoveOffset(refreshFinalMoveOffset);
        }
    }

    public void setRefreshEnabled(boolean enabled) {
        this.mRefreshEnabled = enabled;
    }

    public void setRefreshHeaderView(@LayoutRes int refreshHeaderLayoutRes) {
        ensureRefreshHeaderContainer();
        final View refreshHeader = LayoutInflater.from(getContext()).inflate(refreshHeaderLayoutRes, mRefreshHeaderContainer, false);
        if (refreshHeader != null) {
            setRefreshHeaderView(refreshHeader);
        }
    }

    private void ensureRefreshHeaderContainer() {
        if (mRefreshHeaderContainer == null) {
            mRefreshHeaderContainer = new RefreshHeaderLayout(getContext());
            mRefreshHeaderContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    public void setRefreshHeaderView(View refreshHeaderView) {
        if (!(refreshHeaderView instanceof RefreshTrigger)) {
            throw new ClassCastException("Refresh header view must be an implement of RefreshTrigger");
        }

        if (mRefreshHeaderView != null) {
            removeRefreshHeaderView();
        }

        if (mRefreshHeaderView != refreshHeaderView) {
            this.mRefreshHeaderView = refreshHeaderView;
            ensureRefreshHeaderContainer();

            mRefreshHeaderContainer.addView(refreshHeaderView);
        }
    }

    private void removeRefreshHeaderView() {
        if (mRefreshHeaderContainer != null) {
            mRefreshHeaderContainer.removeView(mRefreshHeaderView);
        }
    }

    public void setRefreshFinalMoveOffset(int refreshFinalMoveOffset) {
        this.mRefreshFinalMoveOffset = refreshFinalMoveOffset;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (mRefreshHeaderView != null) {
            if (mRefreshHeaderView.getMeasuredHeight() > mRefreshFinalMoveOffset) {
                mRefreshFinalMoveOffset = 0;
            }
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        final int action = MotionEventCompat.getActionMasked(motionEvent);
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                setLastTouchPosition(motionEvent, actionIndex);
            }
            break;

            case MotionEvent.ACTION_POINTER_DOWN: {
                mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                setLastTouchPosition(motionEvent, actionIndex);
            }
            break;

            case MotionEventCompat.ACTION_POINTER_UP: {
                onPointerUp(motionEvent);
            }
            break;
        }

        return super.onInterceptTouchEvent(motionEvent);
    }

    private void setLastTouchPosition(MotionEvent motionEvent, int actionIndex) {
        mLastTouchX = (int) (MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
        mLastTouchY = (int) (MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        final int action = MotionEventCompat.getActionMasked(motionEvent);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                handleActionDown(motionEvent);
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                final int index = MotionEventCompat.findPointerIndex(motionEvent, mActivePointerId);
                if (index < 0) {
                    Log.e(TAG, "Error processing scroll; pointer index for id " + index + " not found. Did any MotionEvents get skipped?");
                    return false;
                }

                final int x = getMotionEventX(motionEvent, index);
                final int y = getMotionEventY(motionEvent, index);

                final int dx = x - mLastTouchX;
                final int dy = y - mLastTouchY;

                mLastTouchX = x;
                mLastTouchY = y;

                if (isCheckCanHandleMove()) {

                    final int refreshHeaderContainerHeight = mRefreshHeaderContainer.getMeasuredHeight();
                    final int refreshHeaderViewHeight = mRefreshHeaderView.getMeasuredHeight();

                    if (dy > 0 && mStatus == STATUS_DEFAULT) {
                        setStatus(STATUS_SWIPING_TO_REFRESH);
                    } else if (dy < 0) {
                        if (mStatus == STATUS_SWIPING_TO_REFRESH && refreshHeaderContainerHeight <= 0) {
                            setStatus(STATUS_DEFAULT);
                        }
                        if (mStatus == STATUS_DEFAULT) {
                            break;
                        }
                    }

                    if (mStatus == STATUS_SWIPING_TO_REFRESH || mStatus == STATUS_RELEASE_TO_REFRESH) {
                        if (refreshHeaderContainerHeight >= refreshHeaderViewHeight) {
                            setStatus(STATUS_RELEASE_TO_REFRESH);
                            mRefreshTrigger.switchReleaseAndSwipeStatus(STATUS_RELEASE_TO_REFRESH);
                        } else {
                            setStatus(STATUS_SWIPING_TO_REFRESH);
                            mRefreshTrigger.switchReleaseAndSwipeStatus(STATUS_SWIPING_TO_REFRESH);
                        }

                        fingerMove(dy);
                        return true;
                    }
                }
            }
            break;

            case MotionEventCompat.ACTION_POINTER_DOWN: {
                final int index = MotionEventCompat.getActionIndex(motionEvent);
                mActivePointerId = MotionEventCompat.getPointerId(motionEvent, index);
                mLastTouchX = getMotionEventX(motionEvent, index);
                mLastTouchY = getMotionEventY(motionEvent, index);
            }
            break;

            case MotionEventCompat.ACTION_POINTER_UP: {
                onPointerUp(motionEvent);
            }
            break;

            case MotionEvent.ACTION_UP: {
                onFingerUpStartAnimating();
            }
            break;

            case MotionEvent.ACTION_CANCEL: {
                onFingerUpStartAnimating();
            }
            break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void onFingerUpStartAnimating() {
        if (mStatus == STATUS_RELEASE_TO_REFRESH) {
            startScrollReleaseStatusToRefreshingStatus();
        } else if (mStatus == STATUS_SWIPING_TO_REFRESH) {
            startScrollSwipingToRefreshStatusToDefaultStatus();
        }
    }

    private void startScrollSwipingToRefreshStatusToDefaultStatus() {
        final int targetHeight = 0;
        final int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        startScrollAnimation(300, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollReleaseStatusToRefreshingStatus() {
        mRefreshTrigger.onRelease();

        final int targetHeight = mRefreshHeaderView.getMeasuredHeight();
        final int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        startScrollAnimation(300, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollAnimation(final int time, final Interpolator interpolator, int value, int toValue) {
        if (mScrollAnimator == null) {
            mScrollAnimator = new ValueAnimator();
        }
        //cancel
        mScrollAnimator.removeAllUpdateListeners();
        mScrollAnimator.removeAllListeners();
        mScrollAnimator.cancel();

        //reset new value
        mScrollAnimator.setIntValues(value, toValue);
        mScrollAnimator.setDuration(time);
        mScrollAnimator.setInterpolator(interpolator);
        mScrollAnimator.addUpdateListener(mAnimatorUpdateListener);
        mScrollAnimator.addListener(mAnimationListener);
        mScrollAnimator.start();

    }

    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            final int height = (Integer) animation.getAnimatedValue();
            setRefreshHeaderContainerHeight(height);

            switch (mStatus) {
                case STATUS_SWIPING_TO_REFRESH: {
                }
                break;

                case STATUS_RELEASE_TO_REFRESH: {
                }
                break;

                case STATUS_REFRESHING: {
                }
                break;
            }

        }
    };

    private Animator.AnimatorListener mAnimationListener = new SimpleAnimatorListener() {
        @Override
        public void onAnimationEnd(Animator animation) {
            switch (mStatus) {

                case STATUS_SWIPING_TO_REFRESH: {
                    mRefreshHeaderContainer.getLayoutParams().height = 0;
                    mRefreshHeaderContainer.requestLayout();
                    setStatus(STATUS_DEFAULT);
                }
                break;

                case STATUS_RELEASE_TO_REFRESH: {
                    mRefreshHeaderContainer.getLayoutParams().height = mRefreshHeaderView.getMeasuredHeight();
                    mRefreshHeaderContainer.requestLayout();
                    setStatus(STATUS_REFRESHING);
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onRefresh();
                    }
                }
                break;

                case STATUS_REFRESHING: {
                    mRefreshHeaderContainer.getLayoutParams().height = 0;
                    mRefreshHeaderContainer.requestLayout();
                    setStatus(STATUS_DEFAULT);
                    mRefreshTrigger.onComplete();
                }
                break;
            }

        }
    };

    private void onPointerUp(MotionEvent e) {
        final int actionIndex = MotionEventCompat.getActionIndex(e);
        if (MotionEventCompat.getPointerId(e, actionIndex) == mActivePointerId) {
            // Pick a new pointer to pick up the slack.
            final int newIndex = actionIndex == 0 ? 1 : 0;
            mActivePointerId = MotionEventCompat.getPointerId(e, newIndex);
            mLastTouchX = getMotionEventX(e, newIndex);
            mLastTouchY = getMotionEventY(e, newIndex);
        }
    }

    private boolean isCheckCanHandleMove() {
        return isEnabled() && mRefreshEnabled && mRefreshHeaderView != null && isFingerDragging() && canTriggerRefresh();
    }

    private void handleActionDown(MotionEvent motionEvent) {
        final int index = MotionEventCompat.getActionIndex(motionEvent);
        mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
        mLastTouchX = getMotionEventX(motionEvent, index);
        mLastTouchY = getMotionEventY(motionEvent, index);
    }

    private int getMotionEventX(MotionEvent e, int pointerIndex) {
        return (int) (MotionEventCompat.getX(e, pointerIndex) + 0.5f);
    }

    private int getMotionEventY(MotionEvent e, int pointerIndex) {
        return (int) (MotionEventCompat.getY(e, pointerIndex) + 0.5f);
    }

    private boolean isFingerDragging() {
        return getScrollState() == SCROLL_STATE_DRAGGING;
    }

    public boolean canTriggerRefresh() {
        final Adapter adapter = getAdapter();
        if (adapter == null || adapter.getItemCount() <= 0) {
            return true;
        }
        View firstChild = getChildAt(0);
        int position = getChildLayoutPosition(firstChild);
        if (position == 0) {
            if (firstChild.getTop() == mRefreshHeaderContainer.getTop()) {
                return true;
            }
        }
        return false;
    }

    private void setStatus(int status) {
        this.mStatus = status;
    }

    private void fingerMove(int dy) {
        int ratioDy = (int) (dy * 0.5f + 0.5f);
        int offset = mRefreshHeaderContainer.getMeasuredHeight();
        int finalDragOffset = mRefreshFinalMoveOffset;

        int nextOffset = offset + ratioDy;
        if (finalDragOffset > 0) {
            if (nextOffset > finalDragOffset) {
                ratioDy = finalDragOffset - offset;
            }
        }

        if (nextOffset < 0) {
            ratioDy = -offset;
        }
        move(ratioDy);
    }

    private void move(int dy) {
        if (dy != 0) {
            int height = mRefreshHeaderContainer.getMeasuredHeight() + dy;
            setRefreshHeaderContainerHeight(height);
        }
    }

    private void setRefreshHeaderContainerHeight(int height) {
        mRefreshHeaderContainer.getLayoutParams().height = height;
        mRefreshHeaderContainer.requestLayout();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }

    public void setRefreshing(boolean refreshing) {
        if (mStatus == STATUS_DEFAULT && refreshing) {
            setStatus(STATUS_SWIPING_TO_REFRESH);
        } else if (mStatus == STATUS_REFRESHING && !refreshing) {
            refreshingToDefaultStatus();
        }
    }

    private void refreshingToDefaultStatus() {
        startScrollRefreshingStatusToDefaultStatus();
    }

    private void startScrollRefreshingStatusToDefaultStatus() {
        final int targetHeight = 0;
        final int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        startScrollAnimation(400, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    //支持鼠标悬停等鼠标操作
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        final int actionIndex = MotionEventCompat.getActionIndex(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                setLastTouchPosition(event, actionIndex);
            }
            break;

            case MotionEvent.ACTION_POINTER_DOWN: {
                mActivePointerId = MotionEventCompat.getPointerId(event, actionIndex);
                setLastTouchPosition(event, actionIndex);
            }
            break;

            case MotionEventCompat.ACTION_POINTER_UP: {
                onPointerUp(event);
            }
            break;
        }

        return super.onInterceptTouchEvent(event);
    }


    private RefreshTrigger mRefreshTrigger = new RefreshTrigger() {


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
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshTrigger) {
                RefreshTrigger headerRefreshTrigger = (RefreshTrigger) mRefreshHeaderView;
                headerRefreshTrigger.onRelease();
            }
        }

        @Override
        public void onComplete() {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshTrigger) {
                RefreshTrigger headerRefreshTrigger = (RefreshTrigger) mRefreshHeaderView;
                headerRefreshTrigger.onComplete();
            }
        }

        @Override
        public void onReset() {

        }

        @Override
        public void switchReleaseAndSwipeStatus(int status) {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshTrigger) {
                RefreshTrigger headerRefreshTrigger = (RefreshTrigger) mRefreshHeaderView;
                headerRefreshTrigger.switchReleaseAndSwipeStatus(status);
            }
        }
    };


}
