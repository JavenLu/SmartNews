package javen.example.com.news.main.router.provider;

import com.spinytech.macore.MaProvider;

import javen.example.com.commonlibrary.Constant;
import javen.example.com.news.main.activity.home.NewsSearchActivity;
import javen.example.com.news.main.router.action.ActionHistoryEqQuery;
import javen.example.com.news.main.router.action.ActionHistoryKeyInsert;
import javen.example.com.news.main.router.action.ActionJumpToNewsActivityForShowNewsByHistoryKey;

/**
 * Created by Javen on 08/02/2018.
 */

public class ProviderNews extends MaProvider {
    @Override
    protected void registerActions() {
        registerAction(Constant.QUERY_EQ_HISTORY_KEY_ACTION, new ActionHistoryEqQuery());
        registerAction(Constant.INSERT_HISTORY_KEY_ACTION, new ActionHistoryKeyInsert());
        registerAction(Constant.SHOW_NEWS_ACTIVITY_BY_HISTORY_KEY_ACTION, new ActionJumpToNewsActivityForShowNewsByHistoryKey());
        registerAction(Constant.QUERY_LIKE_HISTORY_KEY_ACTION, new NewsSearchActivity.ActionHistoryLikeQuery());
        registerAction(Constant.CLEAR_HISTORY_KEY_ACTION, new NewsSearchActivity.ActionClearHistoryKey());
    }
}
