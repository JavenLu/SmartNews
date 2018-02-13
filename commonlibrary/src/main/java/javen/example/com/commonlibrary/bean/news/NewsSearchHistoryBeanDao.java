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
 * DAO for table "NEWS_SEARCH_HISTORY_BEAN".
*/
public class NewsSearchHistoryBeanDao extends AbstractDao<NewsSearchHistoryBean, Long> {

    public static final String TABLENAME = "NEWS_SEARCH_HISTORY_BEAN";

    /**
     * Properties of entity NewsSearchHistoryBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NewsKey = new Property(1, String.class, "newsKey", false, "NEWS_KEY");
    }


    public NewsSearchHistoryBeanDao(DaoConfig config) {
        super(config);
    }
    
    public NewsSearchHistoryBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_SEARCH_HISTORY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NEWS_KEY\" TEXT);"); // 1: newsKey
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_SEARCH_HISTORY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsSearchHistoryBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String newsKey = entity.getNewsKey();
        if (newsKey != null) {
            stmt.bindString(2, newsKey);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsSearchHistoryBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String newsKey = entity.getNewsKey();
        if (newsKey != null) {
            stmt.bindString(2, newsKey);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NewsSearchHistoryBean readEntity(Cursor cursor, int offset) {
        NewsSearchHistoryBean entity = new NewsSearchHistoryBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // newsKey
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsSearchHistoryBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNewsKey(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsSearchHistoryBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsSearchHistoryBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsSearchHistoryBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}