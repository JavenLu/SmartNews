package javen.example.com.smartnews.main.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javen.example.com.smartnews.MyApplication;

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


    public static RecyclerView.LayoutManager createLayoutManager(Context context) {
        RecyclerView.LayoutManager layoutManager = null;

        switch (type) {
            case LINEAR_TYPE:
                layoutManager = new LinearLayoutManager(context);
                break;
            case GRID_TYPE:
                layoutManager = new GridLayoutManager(context, layoutSpanCount);
                break;
            case STAGGERED_TYPE:
                layoutManager = new StaggeredGridLayoutManager(layoutSpanCount, layoutOrientation);
                break;
            default:
                break;
        }

        return layoutManager;
    }

    public static void configurationLayoutManagerInformation(int layoutType, int spanCount, int orientation) {
        type = layoutType;
        layoutSpanCount = spanCount;
        layoutOrientation = orientation;
    }

    public static void configurationLayoutManagerInformation(int layoutType, int spanCount) {
        type = layoutType;
        layoutSpanCount = spanCount;
    }

    public static void configurationLayoutManagerInformation(int layoutType) {
        type = layoutType;
    }

    public static View handleStaggeredGridNoFullSpan(LayoutInflater inflater, @NonNull ViewGroup parent, int layoutResourceId) {
        View view = inflater.inflate(layoutResourceId, parent, false);

        if (MyApplication.isStaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.width = StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT;
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
        }

        return view;

    }

}
