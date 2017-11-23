package javen.example.com.smartnews.main.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import javen.example.com.smartnews.MyApplication;


/**
 * Created by LuJun on 01/11/2017.
 */

public class DividerDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private Drawable divider;
    private int dividerBottomMargin;
    private int dividerRightMargin;
    private int type;

    public static final int BOTTOM_LINE_TYPE = 1;
    public static final int RIGHT_LINE_TYPE = 2;
    public static final int BOTH_LINE_TYPE = 3;


    public DividerDecoration(Context context, int dividerBottomMargin, int dividerRightMargin, int type) {
        this.context = context;
        this.dividerBottomMargin = dividerBottomMargin;
        this.dividerRightMargin = dividerRightMargin;
        this.type = type;


        TypedValue typedValue = new TypedValue();
        if (MyApplication.getContext().getTheme().resolveAttribute(android.R.attr.listDivider, typedValue, true) && typedValue.data != 0) {
            this.divider = MyApplication.getContext().getResources().getDrawable(typedValue.resourceId);
        }
    }

    public DividerDecoration(Context context, int resId, int dividerBottomMargin, int dividerRightMargin, int type) {
        this.context = context;
        this.dividerBottomMargin = dividerBottomMargin;
        this.dividerRightMargin = dividerRightMargin;
        this.type = type;

        divider = ContextCompat.getDrawable(context, resId);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        switch (type) {
            case BOTTOM_LINE_TYPE:
                drawDividerOnBottom(c, parent);
                break;

            case RIGHT_LINE_TYPE:
                drawDividerOnRight(c, parent);
                break;

            case BOTH_LINE_TYPE:
                drawDividerOnBottom(c, parent);
                drawDividerOnRight(c, parent);
                break;
        }


    }

    private void drawDividerOnRight(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = child.getRight() + context.getResources().getDimensionPixelOffset(dividerRightMargin);
            int right = left + divider.getIntrinsicWidth();
            int top = child.getTop() + params.topMargin;
            int bottom = child.getBottom() - params.bottomMargin;

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    private void drawDividerOnBottom(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() + context.getResources().getDimensionPixelOffset(dividerBottomMargin);
        int right = parent.getWidth() - parent.getPaddingRight() - context.getResources().getDimensionPixelOffset(dividerBottomMargin);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin + context.getResources().getDimensionPixelOffset(dividerBottomMargin) + 10;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }


}
