package javen.example.com.smartnews.db;

import android.content.Context;

import javen.example.com.smartnews.db.news_channel.DaoMaster;
import javen.example.com.smartnews.db.news_channel.DaoSession;

/**
 * Created by Javen on 16/11/2017.
 */

public class GreenDaoManager {
    private static final String DB_NAME = "SMART_NEWS_DB";

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper helper;

    private static GreenDaoManager greenDaoManager;
    private Context context;

    private GreenDaoManager(Context context) {
        this.context = context;
        mDaoMaster = getDaoMaster();
    }

    public static GreenDaoManager getInstance(Context context) {
        if (greenDaoManager == null) {

            synchronized (GreenDaoManager.class) {
                if (greenDaoManager == null) {
                    greenDaoManager = new GreenDaoManager(context);
                }
            }

        }

        return greenDaoManager;
    }


    /**
     * 判断是否有存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {

        if (mDaoMaster == null) {
            helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }

        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            mDaoSession = mDaoMaster.newSession();
        }

        return mDaoSession;
    }

    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {

        if (helper != null) {
            helper.close();
            helper = null;
        }

    }

    public void closeDaoSession() {

        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }

    }

}
