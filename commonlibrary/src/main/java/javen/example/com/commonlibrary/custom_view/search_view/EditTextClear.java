package javen.example.com.commonlibrary.custom_view.search_view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

import javen.example.com.commonlibrary.R;

/**
 * Created by Javen on 19/01/2018.
 */

public class EditTextClear extends AppCompatEditText {

    private Drawable clearDrawable, searchDrawable;

    public EditTextClear(Context context) {
        this(context, null);
    }

    public EditTextClear(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clearDrawable = getResources().getDrawable(R.drawable.delete_search);
        searchDrawable = getResources().getDrawable(R.drawable.search_icon);
        setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        showDrawable(isShowClearDrawable(text));
    }

    private boolean isShowClearDrawable(CharSequence text) {
        return hasFocus() && text.length() > 0;
    }

    private void showDrawable(boolean visible) {
        setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, visible ? clearDrawable : null, null);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        showDrawable(hasFocus() && length() > 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable = clearDrawable;

                if (isClearDrawableRange(event, drawable)) {
                    setText("");
                }
                break;
        }


        return super.onTouchEvent(event);


    }

    private boolean isClearDrawableRange(MotionEvent event, Drawable drawable) {
        return drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width());
    }

}
