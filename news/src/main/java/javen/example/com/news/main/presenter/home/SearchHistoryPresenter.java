package javen.example.com.news.main.presenter.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javen.example.com.news.R;
import javen.example.com.news.main.activity.home.NewsShowActivity;

/**
 * Created by Javen on 09/02/2018.
 */

public class SearchHistoryPresenter {
    public static void jumpToNewsShowActivity(Context context, String key) {
        Intent intent = new Intent(context, NewsShowActivity.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }
}
