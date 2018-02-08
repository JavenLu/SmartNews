package javen.example.com.commonlibrary.bean.news;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;


/**
 * Created by Javen on 16/11/2017.
 * 阿里云(聚合)的接口数据字段命名不是很规范
 */
@Entity
public class NewsBean extends BaseNewsBean {

    private static final long serialVersionUID = 5151418948645248318L;

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "TITLE")
    private String title;

    @Property(nameInDb = "DATE")
    private String date;

    @Property(nameInDb = "AUTHOR_NAME")
    private String author_name;

    @Property(nameInDb = "THUMBNAIL_PIC_S")
    private String thumbnail_pic_s;

    @Property(nameInDb = "THUMBNAIL_PIC_S02")
    private String thumbnail_pic_s02;

    @Property(nameInDb = "THUMBNAIL_PIC_S03")
    private String thumbnail_pic_s03;

    @Property(nameInDb = "URL")
    private String url;

    @Unique
    @Property(nameInDb = "UNIQUE_KEY")
    private String uniquekey;

    @Property(nameInDb = "TYPE")
    private String type;

    @Property(nameInDb = "REAL_TYPE")
    private String realType;

    @Property(nameInDb = "CATEGORY")
    private String category;

    @Generated(hash = 1817463398)
    public NewsBean(Long id, String title, String date, String author_name,
                    String thumbnail_pic_s, String thumbnail_pic_s02, String thumbnail_pic_s03,
                    String url, String uniquekey, String type, String realType, String category) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.author_name = author_name;
        this.thumbnail_pic_s = thumbnail_pic_s;
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
        this.url = url;
        this.uniquekey = uniquekey;
        this.type = type;
        this.realType = realType;
        this.category = category;
    }

    @Generated(hash = 1662878226)
    public NewsBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return this.author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s() {
        return this.thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return this.thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return this.thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUniquekey() {
        return this.uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealType() {
        return this.realType;
    }

    public void setRealType(String realType) {
        this.realType = realType;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
