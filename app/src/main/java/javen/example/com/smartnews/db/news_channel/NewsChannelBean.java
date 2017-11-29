package javen.example.com.smartnews.db.news_channel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Javen on 29/11/2017.
 */

@Entity
public class NewsChannelBean {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "NEWS_CHANNEL_NAME")
    private String newsChannelName;
    @Property(nameInDb = "NEWS_CHANNEL_ID")
    private String newsChannelId;
    @Property(nameInDb = "NEWS_CHANNEL_TYPE")
    private String newsChannelType;
    @Property(nameInDb = "NEWS_CHANNEL_SELECT")
    private boolean newsChannelSelect;
    @Property(nameInDb = "NEWS_CHANNEL_INDEX")
    private int newsChannelIndex;
    @Property(nameInDb = "NEWS_CHANNEL_FIXED")
    private Boolean newsChannelFixed;
    @Generated(hash = 301612589)
    public NewsChannelBean(Long id, String newsChannelName, String newsChannelId,
            String newsChannelType, boolean newsChannelSelect, int newsChannelIndex,
            Boolean newsChannelFixed) {
        this.id = id;
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }
    @Generated(hash = 654711517)
    public NewsChannelBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNewsChannelName() {
        return this.newsChannelName;
    }
    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }
    public String getNewsChannelId() {
        return this.newsChannelId;
    }
    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }
    public String getNewsChannelType() {
        return this.newsChannelType;
    }
    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }
    public boolean getNewsChannelSelect() {
        return this.newsChannelSelect;
    }
    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }
    public int getNewsChannelIndex() {
        return this.newsChannelIndex;
    }
    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }
    public Boolean getNewsChannelFixed() {
        return this.newsChannelFixed;
    }
    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }
}
