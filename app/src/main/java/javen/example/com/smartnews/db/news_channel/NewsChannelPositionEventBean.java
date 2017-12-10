package javen.example.com.smartnews.db.news_channel;

/**
 * Created by Javen on 08/12/2017.
 */

public class NewsChannelPositionEventBean {
    private int fromPosition;
    private int toPosition;

    public int getFromPosition() {
        return fromPosition;
    }

    public int getToPosition() {
        return toPosition;
    }

    public NewsChannelPositionEventBean(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;

    }
}
