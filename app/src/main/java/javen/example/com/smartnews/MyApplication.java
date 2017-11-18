package javen.example.com.smartnews;

import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

import javen.example.com.smartnews.db.GreenDaoManager;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.DaoSession;
import javen.example.com.smartnews.main.fragment.home.fragments.TopNewsFragment;


/**
 * Created by Javen on 16/11/2017.
 */

public class MyApplication extends Application {
    public static DaoSession daoSession;
    public static HashMap<String, Fragment> typeHashMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = GreenDaoManager.getInstance(this).getDaoSession();
        typeHashMap.put("头条", new TopNewsFragment());
    }
}
