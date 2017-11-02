package com.afford.thirdapi.greedsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table SHOPPING_CART.
*/
public class ShoppingCartDao extends AbstractDao<ShoppingCart, Long> {

    public static final String TABLENAME = "SHOPPING_CART";

    /**
     * Properties of entity ShoppingCart.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ID = new Property(1, String.class, "ID", false, "ID");
        public final static Property ShopID = new Property(2, String.class, "ShopID", false, "SHOP_ID");
        public final static Property Brandname = new Property(3, String.class, "brandname", false, "BRANDNAME");
        public final static Property Name = new Property(4, String.class, "name", false, "NAME");
        public final static Property Spec = new Property(5, String.class, "spec", false, "SPEC");
        public final static Property Shortinfo = new Property(6, String.class, "shortinfo", false, "SHORTINFO");
        public final static Property Marketprice = new Property(7, String.class, "marketprice", false, "MARKETPRICE");
        public final static Property Imgsrc = new Property(8, String.class, "imgsrc", false, "IMGSRC");
        public final static Property Urv = new Property(9, String.class, "urv", false, "URV");
        public final static Property Type = new Property(10, String.class, "type", false, "TYPE");
        public final static Property Sellprice = new Property(11, String.class, "sellprice", false, "SELLPRICE");
        public final static Property Buynum = new Property(12, String.class, "buynum", false, "BUYNUM");
        public final static Property Shoptype = new Property(13, String.class, "shoptype", false, "SHOPTYPE");
        public final static Property Ischoose = new Property(14, Boolean.class, "ischoose", false, "ISCHOOSE");
    };


    public ShoppingCartDao(DaoConfig config) {
        super(config);
    }
    
    public ShoppingCartDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SHOPPING_CART' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'ID' TEXT NOT NULL ," + // 1: ID
                "'SHOP_ID' TEXT NOT NULL ," + // 2: ShopID
                "'BRANDNAME' TEXT," + // 3: brandname
                "'NAME' TEXT," + // 4: name
                "'SPEC' TEXT," + // 5: spec
                "'SHORTINFO' TEXT," + // 6: shortinfo
                "'MARKETPRICE' TEXT," + // 7: marketprice
                "'IMGSRC' TEXT," + // 8: imgsrc
                "'URV' TEXT," + // 9: urv
                "'TYPE' TEXT," + // 10: type
                "'SELLPRICE' TEXT," + // 11: sellprice
                "'BUYNUM' TEXT," + // 12: buynum
                "'SHOPTYPE' TEXT," + // 13: shoptype
                "'ISCHOOSE' INTEGER);"); // 14: ischoose
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SHOPPING_CART'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ShoppingCart entity) {
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
 
        String spec = entity.getSpec();
        if (spec != null) {
            stmt.bindString(6, spec);
        }
 
        String shortinfo = entity.getShortinfo();
        if (shortinfo != null) {
            stmt.bindString(7, shortinfo);
        }
 
        String marketprice = entity.getMarketprice();
        if (marketprice != null) {
            stmt.bindString(8, marketprice);
        }
 
        String imgsrc = entity.getImgsrc();
        if (imgsrc != null) {
            stmt.bindString(9, imgsrc);
        }
 
        String urv = entity.getUrv();
        if (urv != null) {
            stmt.bindString(10, urv);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(11, type);
        }
 
        String sellprice = entity.getSellprice();
        if (sellprice != null) {
            stmt.bindString(12, sellprice);
        }
 
        String buynum = entity.getBuynum();
        if (buynum != null) {
            stmt.bindString(13, buynum);
        }
 
        String shoptype = entity.getShoptype();
        if (shoptype != null) {
            stmt.bindString(14, shoptype);
        }
 
        Boolean ischoose = entity.getIschoose();
        if (ischoose != null) {
            stmt.bindLong(15, ischoose ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ShoppingCart readEntity(Cursor cursor, int offset) {
        ShoppingCart entity = new ShoppingCart( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // ID
            cursor.getString(offset + 2), // ShopID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // brandname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // spec
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // shortinfo
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // marketprice
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // imgsrc
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // urv
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // type
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // sellprice
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // buynum
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // shoptype
            cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0 // ischoose
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ShoppingCart entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.getString(offset + 1));
        entity.setShopID(cursor.getString(offset + 2));
        entity.setBrandname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSpec(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setShortinfo(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMarketprice(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setImgsrc(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUrv(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setType(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSellprice(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setBuynum(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setShoptype(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setIschoose(cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ShoppingCart entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ShoppingCart entity) {
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