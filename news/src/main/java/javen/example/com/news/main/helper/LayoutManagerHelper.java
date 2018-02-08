package javen.example.com.news.main.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.commonlibrary.CommonLibraryApplication;


/**
 * Created by LuJun on 01/11/2017.
 */

public class LayoutManagerHelper {

    public static final int LINEAR_TYPE = 1;
    public static final int GRID_TYPE = 2;
    public static final int STAGGERED_TYPE = 3;


    private static int type;
    private static int layoutSpanCount;
    private static int layoutOrientation;
    private static boolean layoutReverseLayout;


    private static RecyclerView.LayoutManager createLayoutManager(Context context) {
        RecyclerView.LayoutManager layoutManager = null;

        switch (type) {
            case LINEAR_TYPE:
                layoutManager = new LinearLayoutManager(context);
                break;
            case GRID_TYPE:

                if (layoutOrientation != -1) {
                    layoutManager = new GridLayoutManager(context, layoutSpanCount, layoutOrientation, layoutReverseLayout);
                } else {
                    layoutManager = new GridLayoutManager(context, layoutSpanCount);
                }

                break;
            case STAGGERED_TYPE:
                layoutManager = new StaggeredGridLayoutManager(layoutSpanCount, layoutOrientation);
                break;
            default:
                break;
        }

        return layoutManager;
    }

    public static RecyclerView.LayoutManager configurationLayoutManagerInformation(Context context, int layoutType, int spanCount, int orientation, boolean reverseLayout) {
        type = layoutType;
        layoutSpanCount = spanCount;
        layoutOrientation = orientation;
        layoutReverseLayout = reverseLayout;
        return createLayoutManager(context);
    }

    public static RecyclerView.LayoutManager configurationLayoutManagerInformation(Context context, int layoutType, int spanCount, int orientation) {
        type = layoutType;
        layoutSpanCount = spanCount;
        layoutOrientation = orientation;
        return configurationLayoutManagerInformation(context, type, layoutSpanCount, layoutOrientation, false);
    }

    public static RecyclerView.LayoutManager configurationLayoutManagerInformation(Context context, int layoutType, int spanCount) {
        type = layoutType;
        layoutSpanCount = spanCount;
        return configurationLayoutManagerInformation(context, type, layoutSpanCount, -1);
    }

    public static RecyclerView.LayoutManager configurationLayoutManagerInformation(Context context, int layoutType) {
        type = layoutType;
        return configurationLayoutManagerInformation(context, type, 1);
    }

    public static View handleStaggeredGridNoFullSpan(LayoutInflater inflater, @NonNull ViewGroup parent, int layoutResourceId) {
        View view = inflater.inflate(layoutResourceId, parent, false);

        if (CommonLibraryApplication.isStaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.width = StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT;
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
        }

        return view;

    }

}
