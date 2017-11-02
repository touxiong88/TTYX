package com.afford.thirdapi.greedsqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shoppingCartDaoConfig;
    private final DaoConfig searchDaoConfig;
    private final DaoConfig browseHistoryDaoConfig;
    private final DaoConfig serverDaoConfig;

    private final ShoppingCartDao shoppingCartDao;
    private final SearchDao searchDao;
    private final BrowseHistoryDao browseHistoryDao;
    private final ServerDao serverDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shoppingCartDaoConfig = daoConfigMap.get(ShoppingCartDao.class).clone();
        shoppingCartDaoConfig.initIdentityScope(type);

        searchDaoConfig = daoConfigMap.get(SearchDao.class).clone();
        searchDaoConfig.initIdentityScope(type);

        browseHistoryDaoConfig = daoConfigMap.get(BrowseHistoryDao.class).clone();
        browseHistoryDaoConfig.initIdentityScope(type);

        serverDaoConfig = daoConfigMap.get(ServerDao.class).clone();
        serverDaoConfig.initIdentityScope(type);

        shoppingCartDao = new ShoppingCartDao(shoppingCartDaoConfig, this);
        searchDao = new SearchDao(searchDaoConfig, this);
        browseHistoryDao = new BrowseHistoryDao(browseHistoryDaoConfig, this);
        serverDao = new ServerDao(serverDaoConfig, this);

        registerDao(ShoppingCart.class, shoppingCartDao);
        registerDao(Search.class, searchDao);
        registerDao(BrowseHistory.class, browseHistoryDao);
        registerDao(Server.class, serverDao);
    }
    
    public void clear() {
        shoppingCartDaoConfig.getIdentityScope().clear();
        searchDaoConfig.getIdentityScope().clear();
        browseHistoryDaoConfig.getIdentityScope().clear();
        serverDaoConfig.getIdentityScope().clear();
    }

    public ShoppingCartDao getShoppingCartDao() {
        return shoppingCartDao;
    }

    public SearchDao getSearchDao() {
        return searchDao;
    }

    public BrowseHistoryDao getBrowseHistoryDao() {
        return browseHistoryDao;
    }

    public ServerDao getServerDao() {
        return serverDao;
    }

}
