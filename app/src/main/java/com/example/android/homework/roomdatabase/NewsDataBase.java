package com.example.android.homework.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.homework.NewsItem;


@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsDataBase extends RoomDatabase {

    public abstract NewsItemDao newsQueriesDao();

    private static NewsDataBase INSTANCE;

    public static NewsDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , NewsDataBase.class, "NewsDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
