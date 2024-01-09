package com.jyh.rest.utils;

import android.content.Context;


import com.jyh.rest.entity.DaoMaster;
import com.jyh.rest.entity.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

/**
 * Created by sunxx on 2016/11/19.
 */

public class GreenDaoMananger {
    private static GreenDaoMananger instance;
    private static DaoSession daoSession;

    private GreenDaoMananger(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "favourite-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public static GreenDaoMananger getInstance(Context context) {
        if (instance == null) {
            instance = new GreenDaoMananger(context);
        }
        return instance;
    }

    public DaoSession getDaoSession() {

        return daoSession;
    }

    public AbstractDao getDao(Class clazz) {
        return daoSession.getDao(clazz);
    }

}
