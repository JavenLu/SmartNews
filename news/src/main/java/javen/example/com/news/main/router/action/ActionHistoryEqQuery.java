package javen.example.com.news.main.router.action;

import android.content.Context;
import android.text.TextUtils;

import com.spinytech.macore.MaAction;
import com.spinytech.macore.MaActionResult;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.HashMap;
import java.util.List;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.Constant;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBean;
import javen.example.com.commonlibrary.bean.news.NewsSearchHistoryBeanDao;
import javen.example.com.news.main.model.home.SearchHistoryModel;


/**
 * Created by Javen on 08/02/2018.
 */

public class ActionHistoryEqQuery extends MaAction {
    @Override
    public boolean isAsync(Context context, HashMap<String, String> requestData) {
        return false;
    }

    @Override
    public MaActionResult invoke(Context context, HashMap<String, String> requestData) {
        if (requestData != null) {
            String historyKey = requestData.get(Constant.HISTORY_KEY_NAME);

            if (!TextUtils.isEmpty(historyKey)) {
                List<NewsSearchHistoryBean> list = SearchHistoryModel.queryHistoryKey(historyKey);

                int resultCode = 0;

                if (list != null && list.size() > 0) {
                    resultCode = MaActionResult.CODE_SUCCESS;
                } else {
                    resultCode = MaActionResult.CODE_ERROR;
                }

                return new MaActionResult.Builder()
                        .code(resultCode)
                        .build();

            }
        }
        return null;
    }
}
