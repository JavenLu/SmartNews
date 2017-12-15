package javen.example.com.smartnews;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import javen.example.com.smartnews.db.GreenDaoManager;
import javen.example.com.smartnews.db.news_channel.DaoSession;


/**
 * Created by Javen on 16/11/2017.
 */

public class MyApplication extends Application {
    public static DaoSession daoSession;
    public static TreeMap<String, String> typeTreeMap = new TreeMap<>();
    public static boolean isStaggeredGridLayoutManager = false;
    private static Context context;
    public static List<String> channelName;
    public static List<String> newsType;
    public static int screenWidth, screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = GreenDaoManager.getInstance(this).getDaoSession();
        channelName = Arrays.asList(getResources().getStringArray(R.array.news_channel_name));
        newsType = Arrays.asList(getResources().getStringArray(R.array.news_request_type));

        for (int i = 0; i < channelName.size(); i++) {
            typeTreeMap.put(channelName.get(i), newsType.get(i));
        }

        context = getApplicationContext();

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    public static Context getContext() {
        return context;
    }
}
