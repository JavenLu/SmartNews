package javen.example.com.news.db;

/**
 * Created by Javen on 01/12/2017.
 */

public class DBConstant {

    //头条
    public static final String NEWS_HEAD_ID = "T1348647909107";

    // 头条TYPE
    public static final String NEWS_HEAD = "news_head";

    // 其他TYPE
    public static final String OTHER_TYPE = "other_type";

    public static final int NEWS_CHANNEL_MINE = 0;
    public static final int NEWS_CHANNEL_MORE = 1;

    public static String getType(String id) {
        switch (id) {
            case NEWS_HEAD_ID:
                return NEWS_HEAD;
            default:
                break;
        }
        return OTHER_TYPE;
    }
}
