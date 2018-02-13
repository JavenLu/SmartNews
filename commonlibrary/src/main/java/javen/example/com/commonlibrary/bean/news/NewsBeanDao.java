package javen.example.com.commonlibrary.bean.news;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_BEAN".
*/
public class NewsBeanDao extends AbstractDao<NewsBean, Long> {

    public static final String TABLENAME = "NEWS_BEAN";

    /**
     * Properties of entity NewsBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Date = new Property(2, String.class, "date", false, "DATE");
        public final static Property Author_name = new Property(3, String.class, "author_name", false, "AUTHOR_NAME");
        public final static Property Thumbnail_pic_s = new Property(4, String.class, "thumbnail_pic_s", false, "THUMBNAIL_PIC_S");
        public final static Property Thumbnail_pic_s02 = new Property(5, String.class, "thumbnail_pic_s02", false, "THUMBNAIL_PIC_S02");
        public final static Property Thumbnail_pic_s03 = new Property(6, String.class, "thumbnail_pic_s03", false, "THUMBNAIL_PIC_S03");
        public final static Property Url = new Property(7, String.class, "url", false, "URL");
        public final static Property Uniquekey = new Property(8, String.class, "uniquekey", false, "UNIQUE_KEY");
        public final static Property Type = new Property(9, String.class, "type", false, "TYPE");
        public final static Property RealType = new Property(10, String.class, "realType", false, "REAL_TYPE");
        public final static Property Category = new Property(11, String.class, "category", false, "CATEGORY");
    }


    public NewsBeanDao(DaoConfig config) {
        super(config);
    }
    
    public NewsBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"DATE\" TEXT," + // 2: date
                "\"AUTHOR_NAME\" TEXT," + // 3: author_name
                "\"THUMBNAIL_PIC_S\" TEXT," + // 4: thumbnail_pic_s
                "\"THUMBNAIL_PIC_S02\" TEXT," + // 5: thumbnail_pic_s02
                "\"THUMBNAIL_PIC_S03\" TEXT," + // 6: thumbnail_pic_s03
                "\"URL\" TEXT," + // 7: url
                "\"UNIQUE_KEY\" TEXT UNIQUE ," + // 8: uniquekey
                "\"TYPE\" TEXT," + // 9: type
                "\"REAL_TYPE\" TEXT," + // 10: realType
                "\"CATEGORY\" TEXT);"); // 11: category
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
 
        String author_name = entity.getAuthor_name();
        if (author_name != null) {
            stmt.bindString(4, author_name);
        }
 
        String thumbnail_pic_s = entity.getThumbnail_pic_s();
        if (thumbnail_pic_s != null) {
            stmt.bindString(5, thumbnail_pic_s);
        }
 
        String thumbnail_pic_s02 = entity.getThumbnail_pic_s02();
        if (thumbnail_pic_s02 != null) {
            stmt.bindString(6, thumbnail_pic_s02);
        }
 
        String thumbnail_pic_s03 = entity.getThumbnail_pic_s03();
        if (thumbnail_pic_s03 != null) {
            stmt.bindString(7, thumbnail_pic_s03);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(8, url);
        }
 
        String uniquekey = entity.getUniquekey();
        if (uniquekey != null) {
            stmt.bindString(9, uniquekey);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(10, type);
        }
 
        String realType = entity.getRealType();
        if (realType != null) {
            stmt.bindString(11, realType);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(12, category);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
 
        String author_name = entity.getAuthor_name();
        if (author_name != null) {
            stmt.bindString(4, author_name);
        }
 
        String thumbnail_pic_s = entity.getThumbnail_pic_s();
        if (thumbnail_pic_s != null) {
            stmt.bindString(5, thumbnail_pic_s);
        }
 
        String thumbnail_pic_s02 = entity.getThumbnail_pic_s02();
        if (thumbnail_pic_s02 != null) {
            stmt.bindString(6, thumbnail_pic_s02);
        }
 
        String thumbnail_pic_s03 = entity.getThumbnail_pic_s03();
        if (thumbnail_pic_s03 != null) {
            stmt.bindString(7, thumbnail_pic_s03);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(8, url);
        }
 
        String uniquekey = entity.getUniquekey();
        if (uniquekey != null) {
            stmt.bindString(9, uniquekey);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(10, type);
        }
 
        String realType = entity.getRealType();
        if (realType != null) {
            stmt.bindString(11, realType);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(12, category);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NewsBean readEntity(Cursor cursor, int offset) {
        NewsBean entity = new NewsBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // date
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author_name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // thumbnail_pic_s
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // thumbnail_pic_s02
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // thumbnail_pic_s03
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // url
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // uniquekey
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // type
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // realType
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // category
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAuthor_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setThumbnail_pic_s(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setThumbnail_pic_s02(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setThumbnail_pic_s03(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUniquekey(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setType(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setRealType(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCategory(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
