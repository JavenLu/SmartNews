package javen.example.com.commonlibrary.bean.news;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Javen on 31/01/2018.
 */

@Entity
public class NewsSearchHistoryBean {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "NEWS_KEY")
    private String newsKey;

    @Generated(hash = 441012587)
    public NewsSearchHistoryBean(Long id, String newsKey) {
        this.id = id;
        this.newsKey = newsKey;
    }

    @Generated(hash = 838071036)
    public NewsSearchHistoryBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsKey() {
        return newsKey;
    }

    public void setNewsKey(String newsKey) {
        this.newsKey = newsKey;
    }
}
