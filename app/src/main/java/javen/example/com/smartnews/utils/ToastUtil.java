package javen.example.com.smartnews.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javen.example.com.smartnews.R;

/**
 * Created by Javen on 10/01/2018.
 */

public class ToastUtil {
    private static Toast toast;
    private static TextView toastContentTextView;

    private static ToastUtil toastUtil;
    private static Context myContext;

    public ToastUtil() {
    }

    public static ToastUtil getInstance(Context context) {
        myContext = context;

        if (toastUtil == null) {
            toastUtil = new ToastUtil();
            initToast(context);
        }

        return toastUtil;
    }

    public static void initToast(Context context) {
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);
        toastContentTextView = view.findViewById(R.id.toast_content);
        toast.setView(view);
    }

    public void setDuration(int duration) {
        toast.setDuration(duration);
    }

    public void setText(String content) {
        toastContentTextView.setText(content);
    }

    public Toast makeText(String text, int duration) {
        setDuration(duration);
        setText(text);
        return toast;
    }

    public void show() {
        toast.show();
    }


    public void showNotConnectInternetToast() {
        setText(myContext.getResources().getString(R.string.not_connect_internet_content));
        setDuration(Toast.LENGTH_SHORT);
        show();
    }
}
