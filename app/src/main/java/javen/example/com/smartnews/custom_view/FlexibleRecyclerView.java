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

    public RecyclerView.LayoutManager createLayoutManager(int layoutType, int spanCount, int orientation, boolean reverseLayout) {

        return LayoutManagerHelper.configurationLayoutManagerInformation(context, layoutType, spanCount, orientation, reverseLayout);
    }


    public RecyclerView.LayoutManager createLayoutManager(int layoutType, int spanCount, int orientation) {

        return LayoutManagerHelper.configurationLayoutManagerInformation(context, layoutType, spanCount, orientation);
    }

    public RecyclerView.LayoutManager createLayoutManager(int layoutType, int spanCount) {

        return LayoutManagerHelper.configurationLayoutManagerInformation(context, layoutType, spanCount);
    }

    public RecyclerView.LayoutManager createLayoutManager(int layoutType) {

        return LayoutManagerHelper.configurationLayoutManagerInformation(context, layoutType);
    }




}
