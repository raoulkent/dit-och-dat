package com.example.simplegolf.model;

import android.content.Context;

import androidx.room.Room;

import com.example.simplegolf.model.database.AppDatabase;

/**
 * Singleton Repository, holds Database and Remote service
 */
public class Repository {
    private static Repository INSTANCE;
    private AppDatabase db;

    private Repository(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "golf-db")
                .fallbackToDestructiveMigration().build();
    }

    /**
     * Gets a single instance of the repository, improves performance.
     *
     * @param context Context of the activity calling this method
     * @return The repository instance
     */
    public static Repository getRepository(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }

    /**
     * Gets access to the database
     *
     * @return database object
     */
    public AppDatabase getDb() {
        return db;
    }
}

/* Examples how to use:
// INSERT & SELECT / This version seems to work best.
new Thread(() -> {
    db.entityDAO.insert(...); // Insert
    Xyz xyz = db.entityDAO.getXyz(); // Select
}).start();


// Old methods below, not working great. See above instead.
// INSERTS / Completable
db.entityDAO().insert(...)
        .subscribeOn(Schedulers.io())
        .subscribe();

// SELECTS / LiveData
db.entityDAO().get(...).observe(this, data -> {
      // Do stuff here, Data is the callback variable.
});*/