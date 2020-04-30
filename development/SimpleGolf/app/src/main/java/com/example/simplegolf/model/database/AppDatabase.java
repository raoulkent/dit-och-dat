package com.example.simplegolf.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {TestEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntityDAO entityDAO();
}
