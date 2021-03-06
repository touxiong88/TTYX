package com.afford.thirdapi.greedsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table SERVER.
*/
public class ServerDao extends AbstractDao<Server, Long> {

    public static final String TABLENAME = "SERVER";

    /**
     * Properties of entity Server.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property ShopID = new Property(2, String.class, "ShopID", false, "SHOP_ID");
        public final static Property Brandname = new Property(3, String.class, "brandname", false, "BRANDNAME");
        public final static Property Name = new Property(4, String.class, "name", false, "NAME");
        public final static Property Thumb = new Property(5, String.class, "thumb", false, "THUMB");
        public final static Property Comment = new Property(6, String.class, "comment", false, "COMMENT");
        public final static Property Marketprice = new Property(7, String.class, "marketprice", false, "MARKETPRICE");
        public final static Property Sellprice = new Property(8, String.class, "sellprice", false, "SELLPRICE");
        public final static Property Title = new Property(9, String.class, "title", false, "TITLE");
        public final static Property ServerID = new Property(10, String.class, "serverID", false, "SERVER_ID");
        public final static Property Shortinfo = new Property(11, String.class, "shortinfo", false, "SHORTINFO");
        public final static Property Buynum = new Property(12, String.class, "buynum", false, "BUYNUM");
        public final static Property Ischoose = new Property(13, Boolean.class, "ischoose", false, "ISCHOOSE");
    };


    public ServerDao(DaoConfig config) {
        super(config);
    }
    
    public ServerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SERVER' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'ID' TEXT NOT NULL ," + // 1: ID
                "'SHOP_ID' TEXT NOT NULL ," + // 2: ShopID
                "'BRANDNAME' TEXT," + // 3: brandname
                "'NAME' TEXT," + // 4: name
                "'THUMB' TEXT," + // 5: thumb
                "'COMMENT' TEXT," + // 6: comment
                "'MARKETPRICE' TEXT," + // 7: marketprice
                "'SELLPRICE' TEXT," + // 8: sellprice
                "'TITLE' TEXT," + // 9: title
                "'SERVER_ID' TEXT," + // 10: serverID
                "'SHORTINFO' TEXT," + // 11: shortinfo
                "'BUYNUM' TEXT," + // 12: buynum
                "'ISCHOOSE' INTEGER);"); // 13: ischoose
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SERVER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Server entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getID());
        stmt.bindString(3, entity.getShopID());
 
        String brandname = entity.getBrandname();
        if (brandname != null) {
            stmt.bindString(4, brandname);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
 
        String thumb = entity.getThumb();
        if (thumb != null) {
            stmt.bindString(6, thumb);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(7, comment);
        }
 
        String marketprice = entity.getMarketprice();
        if (marketprice != null) {
            stmt.bindString(8, marketprice);
        }
 
        String sellprice = entity.getSellprice();
        if (sellprice != null) {
            stmt.bindString(9, sellprice);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(10, title);
        }
 
        String serverID = entity.getServerID();
        if (serverID != null) {
            stmt.bindString(11, serverID);
        }
 
        String shortinfo = entity.getShortinfo();
        if (shortinfo != null) {
            stmt.bindString(12, shortinfo);
        }
 
        String buynum = entity.getBuynum();
        if (buynum != null) {
            stmt.bindString(13, buynum);
        }
 
        Boolean ischoose = entity.getIschoose();
        if (ischoose != null) {
            stmt.bindLong(14, ischoose ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Server readEntity(Cursor cursor, int offset) {
        Server entity = new Server( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // ID
            cursor.getString(offset + 2), // ShopID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // brandname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // thumb
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // comment
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // marketprice
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // sellprice
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // title
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // serverID
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // shortinfo
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // buynum
            cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13) != 0 // ischoose
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Server entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.getString(offset + 1));
        entity.setShopID(cursor.getString(offset + 2));
        entity.setBrandname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setThumb(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setComment(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMarketprice(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSellprice(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTitle(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setServerID(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setShortinfo(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setBuynum(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setIschoose(cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Server entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Server entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
