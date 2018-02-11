package javen.example.com.commonlibrary.local_router;

import android.content.Context;

import com.spinytech.macore.MaApplication;
import com.spinytech.macore.router.LocalRouter;
import com.spinytech.macore.router.RouterRequest;
import com.spinytech.macore.router.RouterResponse;

import javen.example.com.commonlibrary.Constant;

/**
 * Created by Javen on 08/02/2018.
 */

public class RouterManager {

    public static RouterResponse executeNewsHistoryKeyEqQuery(Context context, String key) {
        RouterResponse response = null;

        try {
            response = LocalRouter.getInstance(MaApplication.getMaApplication())
                    .route(context, RouterRequest.obtain(context)
                            .provider(Constant.NEWS_PROVIDER)
                            .action(Constant.QUERY_EQ_HISTORY_KEY_ACTION)
                            .data(Constant.HISTORY_KEY_NAME, key));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static RouterResponse executeShowHistoryKey(Context context, String key) {
        RouterResponse response = null;

        try {
            response = LocalRouter.getInstance(MaApplication.getMaApplication())
                    .route(context, RouterRequest.obtain(context)
                            .provider(Constant.NEWS_PROVIDER)
                            .action(Constant.SHOW_HISTORY_KEY_ACTION)
                            .data(Constant.HISTORY_KEY_NAME, key));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    public static RouterResponse executeNewsHistoryKeyInsert(Context context, String key) {
        RouterResponse response = null;

        try {
            response = LocalRouter.getInstance(MaApplication.getMaApplication())
                    .route(context, RouterRequest.obtain(context)
                            .provider(Constant.NEWS_PROVIDER)
                            .action(Constant.INSERT_HISTORY_KEY_ACTION)
                            .data(Constant.HISTORY_KEY_NAME, key));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static void executeJumpToNewsShowActivity(Context context, String key) {
        try {
            LocalRouter.getInstance(MaApplication.getMaApplication())
                    .route(context, RouterRequest.obtain(context)
                            .provider(Constant.NEWS_PROVIDER)
                            .action(Constant.SHOW_NEWS_ACTIVITY_BY_HISTORY_KEY_ACTION)
                            .data(Constant.HISTORY_KEY_NAME, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static RouterResponse executeNewsHistoryKeyLikeQuery(Context context, String key) {
        RouterResponse response = null;

        try {
            response = LocalRouter.getInstance(MaApplication.getMaApplication())
                    .route(context, RouterRequest.obtain(context)
                            .provider(Constant.NEWS_PROVIDER)
                            .action(Constant.QUERY_LIKE_HISTORY_KEY_ACTION)
                            .data(Constant.HISTORY_KEY_NAME, key));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static RouterResponse executeClearNewsHistoryKey(Context context) {
        RouterResponse response = null;

        try {
            response = LocalRouter.getInstance(MaApplication.getMaApplication())
                    .route(context, RouterRequest.obtain(context)
                            .provider(Constant.NEWS_PROVIDER)
                            .action(Constant.CLEAR_HISTORY_KEY_ACTION));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
