package javen.example.com.smartnews.custom_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import javen.example.com.smartnews.R;

/**
 * Created by Javen on 24/11/2017.
 */

public class CustomToolBar extends FrameLayout {
    private Toolbar toolbar;
    private TextView toolbarTitleTextView;
    private OnClickListener onClickNavigationListener;
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener;
    private ToolbarTitleClickListener toolbarTitleClickListener;

    public CustomToolBar(@NonNull Context context) {
        this(context, null);
    }

    public CustomToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.custom_toolbar_layout, this);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitleTextView = findViewById(R.id.toolbar_title_text_view);
    }

    /**
     * 点击左侧图标事件
     */
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        this.onClickNavigationListener = onClickListener;
        toolbar.setNavigationOnClickListener(onClickNavigationListener);
    }

    /**
     * 设置左边图标
     *
     * @param iconId 图标ID
     */
    public void setNavigationIcon(int iconId) {
        toolbar.setNavigationIcon(iconId);
    }

    /**
     * 去掉左边图标
     */
    public void clearNavigationIcon() {
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(null);
    }


    public void setMenu(int menuId) {
        if (menuId > 0) {

            if (toolbar != null) {
                toolbar.getMenu().clear();

                if (menuId > 0) {
                    toolbar.inflateMenu(menuId);
                }
            }
        }
    }

    public Menu getMenu() {

        if (toolbar != null) {

            return toolbar.getMenu();
        }

        return null;
    }

    /**
     * toolbar 右侧的menu不显示
     */
    public void setMenuGone() {
        if (toolbar != null) {
            toolbar.getMenu().clear();
        }

    }

    /**
     * 点击menu事件
     */
    public void setMenuListener(Toolbar.OnMenuItemClickListener listener) {
        this.onMenuItemClickListener = listener;
        if (toolbar != null) {
            toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        }
    }

    /**
     * 设置标题
     *
     * @param title title
     */
    public void setTitle(String title) {
        toolbarTitleTextView.setText(title);
    }

    /**
     * 设置标题
     *
     * @param title title
     */
    public void setTitle(int title) {
        toolbarTitleTextView.setText(getContext().getResources().getString(title));
    }

    /**
     * 设置标题位置
     *
     * @param gravity
     */
    public void setTitleGravity(int gravity) {
        toolbarTitleTextView.setGravity(gravity);
    }

    public void setTitleSize(int size) {
        toolbarTitleTextView.setTextSize(size);
    }

    /**
     * 设置标题颜色
     *
     * @param color color
     */
    public void setTextColor(int color) {
        toolbarTitleTextView.setTextColor(color);
    }


    /**
     * 设置背景颜色
     *
     * @param color color
     */
    public void setBackgroundColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    /**
     * 点击标题事件
     */
    public void setToolbarTitleOnClickListener(ToolbarTitleClickListener onClickListener) {
        this.toolbarTitleClickListener = onClickListener;
        toolbarTitleTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarTitleClickListener.toolbarTitleListener();
            }
        });


    }


    /**
     * ToolBar标题文字点击接口
     */
    public interface ToolbarTitleClickListener {
        void toolbarTitleListener();
    }

}
