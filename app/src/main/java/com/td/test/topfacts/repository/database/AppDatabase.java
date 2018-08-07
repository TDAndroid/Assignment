package com.td.test.topfacts.repository.database;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.td.test.topfacts.repository.database.dao.FactsModelDao;
import com.td.test.topfacts.repository.database.model.FactsModel;
import com.td.test.topfacts.util.AppConstants;

@Database(entities = {FactsModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Application appContext) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(appContext, AppDatabase.class, AppConstants.APP_DB)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
            }
        }
        return INSTANCE;
    }

    public abstract FactsModelDao getFactsModelDao();
}
