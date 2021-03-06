package javen.example.com.commonlibrary.utils;

import android.text.TextUtils;

import java.util.List;

import javen.example.com.commonlibrary.net.NetConstants;
import javen.example.com.commonlibrary.net.NewsResultBean;
import retrofit2.Response;


/**
 * Created by Javen on 22/11/2017.
 */

public class CheckUtil {

    private static CheckUtil checkUtil;

    public CheckUtil() {

    }

    public static CheckUtil getInstance() {
        if (checkUtil == null) {
            checkUtil = new CheckUtil();
        }

        return checkUtil;
    }


    public boolean isCheckListUsable(List list) {
        return list != null && list.size() > 0;
    }

    public boolean isCheckResponseAvailable(Response<NewsResultBean> response) {
        return response != null && response.body() != null && !TextUtils.isEmpty(response.body().getReason()) && NetConstants.SUCCESS_REASON.equals(response.body().getReason());
    }

    public boolean isCheckTopNewsListNotNull(Response<NewsResultBean> response) {
        return response.body().getResult().getData() != null && response.body().getResult().getData().size() > 0;
    }
}
