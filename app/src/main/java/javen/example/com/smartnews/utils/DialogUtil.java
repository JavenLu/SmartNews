package javen.example.com.smartnews.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Javen on 25/11/2017.
 */

public class DialogUtil {
    private static DialogUtil dialogUtil;

    public static DialogUtil getInstance() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

    public void showCustomBottomDialog(Context context, int dialogStyle, int dialogLayout, int windowAnimationStyle) {
        Dialog dialog = new Dialog(context, dialogStyle);
        View contentView = LayoutInflater.from(context).inflate(dialogLayout, null);
        dialog.setContentView(contentView);

        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setWindowAnimations(windowAnimationStyle);
        }

        dialog.show();
    }


}
