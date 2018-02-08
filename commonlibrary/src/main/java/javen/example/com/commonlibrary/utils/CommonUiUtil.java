package javen.example.com.commonlibrary.utils;

import android.support.design.widget.TabLayout;
import android.view.View;

import javen.example.com.commonlibrary.CommonLibraryApplication;


/**
 * Created by Javen on 15/12/2017.
 */

public class CommonUiUtil {
    private static CommonUiUtil commonUiUtil;

    public static CommonUiUtil getInstance() {
        if (commonUiUtil == null) {
            commonUiUtil = new CommonUiUtil();
        }
        return commonUiUtil;
    }

    public void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabTotalWidth = calculateTabTotalWidth(tabLayout);
        int screenWidth = CommonLibraryApplication.screenWidth;

        if (tabTotalWidth <= screenWidth) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

    }

    private int calculateTabTotalWidth(TabLayout tabLayout) {
        int tabWidth = 0;

        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            View view = tabLayout.getChildAt(i);
            view.measure(0, 0);
            tabWidth += view.getMeasuredWidth();
        }

        return tabWidth;
    }


}
