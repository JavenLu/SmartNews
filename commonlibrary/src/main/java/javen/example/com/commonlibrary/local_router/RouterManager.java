package javen.example.com.commonlibrary.local_router;

import android.content.Context;
import android.content.Intent;

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
                            .action(Constant.QUERY_LIKE_HISTORY_KEY_ACTION)
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

    public static void executeJumpToNewsShowActivity(Context context,String key) {
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
}
