package javen.example.com.commonlibrary;

import android.content.Context;

import com.spinytech.macore.MaApplication;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import javen.example.com.commonlibrary.bean.news.DaoSession;
import javen.example.com.commonlibrary.green_dao.GreenDaoManager;


/**
 * Created by Javen on 16/11/2017.
 */

public class CommonLibraryApplication extends MaApplication {
    public static TreeMap<String, String> typeTreeMap = new TreeMap<>();
    public static DaoSession daoSession;
    public static int screenWidth, screenHeight;
    public static Context context;
    public static boolean isStaggeredGridLayoutManager = false;
    public static List<String> channelName;
    public static List<String> newsType;
    public static boolean isChannelChange = false;
    public static boolean isEnterApp = true;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = GreenDaoManager.getInstance(this).getDaoSession();
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        channelName = Arrays.asList(getResources().getStringArray(R.array.news_channel_name));
        newsType = Arrays.asList(getResources().getStringArray(R.array.news_request_type));

        for (int i = 0; i < channelName.size(); i++) {
            typeTreeMap.put(channelName.get(i), newsType.get(i));
        }

        context = getApplicationContext();
    }

    @Override
    public void initializeAllProcessRouter() {

    }

    @Override
    protected void initializeLogic() {

    }

    @Override
    public boolean needMultipleProcess() {
        return false;
    }

    public static Context getContext() {
        return context;
    }


}
