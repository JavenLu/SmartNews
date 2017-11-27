package javen.example.com.smartnews.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import javen.example.com.smartnews.R;

/**
 * Created by Javen on 27/11/2017.
 */

public class WindowUtil {
    protected boolean useThemeStatusBarColor = true;

    private static WindowUtil windowUtil;

    public static WindowUtil getInstance() {
        if (windowUtil == null) {
            windowUtil = new WindowUtil();
        }
        return windowUtil;
    }

    /**
     * 设置状态栏文字颜色及图标为深色
     * android6.0以后可以对状态栏文字颜色和图标进行修改
     *
     * @param activity
     */
    public void setStatusBarTextAndIconDark(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * @param activity
     */
    public void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

            //根据上面设置是否对状态栏单独设置颜色
            if (useThemeStatusBarColor) {
                activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        setStatusBarTextAndIconDark(activity);
    }


}
