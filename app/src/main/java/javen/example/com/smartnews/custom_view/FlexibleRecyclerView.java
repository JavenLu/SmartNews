package javen.example.com.smartnews.custom_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import javen.example.com.smartnews.main.helper.LayoutManagerHelper;

/**
 * Created by LuJun on 01/11/2017.
 */

public class FlexibleRecyclerView extends RecyclerView {
    private Context context;

    public FlexibleRecyclerView(Context context) {
        super(context);
    }

    public FlexibleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexibleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }


    public RecyclerView.LayoutManager createStaggeredLayoutManager(int layoutType, int spanCount, int orientation) {
        LayoutManagerHelper.configurationLayoutManagerInformation(layoutType, spanCount, orientation);
        return LayoutManagerHelper.createLayoutManager(context);
    }

    public RecyclerView.LayoutManager createGridLayoutManager(int layoutType, int spanCount) {
        LayoutManagerHelper.configurationLayoutManagerInformation(layoutType, spanCount);
        return LayoutManagerHelper.createLayoutManager(context);
    }

    public RecyclerView.LayoutManager createLinearLayoutManager(int layoutType) {
        LayoutManagerHelper.configurationLayoutManagerInformation(layoutType);
        return LayoutManagerHelper.createLayoutManager(context);
    }


}
