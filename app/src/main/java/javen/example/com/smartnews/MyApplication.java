package javen.example.com.smartnews;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.HashMap;

import javen.example.com.smartnews.db.GreenDaoManager;
import javen.example.com.smartnews.db.news_channel.DaoSession;
import javen.example.com.smartnews.main.fragment.home.fragments.NewsFragment;


/**
 * Created by Javen on 16/11/2017.
 */

public class MyApplication extends Application {
    public static DaoSession daoSession;
    public static HashMap<String, Fragment> typeHashMap = new HashMap<>();
    public static boolean isStaggeredGridLayoutManager = false;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = GreenDaoManager.getInstance(this).getDaoSession();
        typeHashMap.put("头条", new NewsFragment());
        context = getApplicationContext();
    }

    public  static Context getContext() {
        return context;
    }
}
