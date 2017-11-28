package javen.example.com.smartnews.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import javen.example.com.smartnews.R;

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

    public void showCustomBottomShareDialog(Context context, int dialogStyle, int dialogLayout, int animationStyle) {
        final Dialog dialog = new Dialog(context, dialogStyle);
        View contentView = LayoutInflater.from(context).inflate(dialogLayout, null);
        TextView textView = contentView.findViewById(R.id.cancel_text_view);
        LinearLayout friendLayout = contentView.findViewById(R.id.friend_layout);
        LinearLayout weChatLayout = contentView.findViewById(R.id.we_chat_layout);
        LinearLayout qqLayout = contentView.findViewById(R.id.qq_layout);

        //设置动画
        AnimationUtil.getInstance().showYShakePropertyAnimationInShareDialog(friendLayout, 40, 800);
        AnimationUtil.getInstance().showYShakePropertyAnimationInShareDialog(weChatLayout, 40, 850);
        AnimationUtil.getInstance().showYShakePropertyAnimationInShareDialog(qqLayout, 40, 900);

        //取消点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(contentView);

        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = context.getResources().getDisplayMetrics().heightPixels / 4;
        contentView.setLayoutParams(layoutParams);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setWindowAnimations(animationStyle);
        }

        dialog.show();
    }


}
