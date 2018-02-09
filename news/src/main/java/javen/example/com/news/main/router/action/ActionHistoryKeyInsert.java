package javen.example.com.news.main.router.action;

import android.content.Context;
import android.text.TextUtils;

import com.spinytech.macore.MaAction;
import com.spinytech.macore.MaActionResult;

import java.util.HashMap;

import javen.example.com.commonlibrary.Constant;
import javen.example.com.news.main.model.home.SearchHistoryModel;

/**
 * Created by Javen on 09/02/2018.
 */

public class ActionHistoryKeyInsert extends MaAction {
    @Override
    public boolean isAsync(Context context, HashMap<String, String> requestData) {
        return false;
    }

    @Override
    public MaActionResult invoke(Context context, HashMap<String, String> requestData) {


        if (requestData != null) {


            String historyKey = requestData.get(Constant.HISTORY_KEY_NAME);
            if (!TextUtils.isEmpty(historyKey)) {
                SearchHistoryModel.insertHistoryKey(historyKey);
            }

            return new MaActionResult.Builder()
                    .code(MaActionResult.CODE_SUCCESS)
                    .build();


        }

        return null;
    }
}
