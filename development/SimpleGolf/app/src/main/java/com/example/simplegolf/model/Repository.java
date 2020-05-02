package com.example.simplegolf.model;

import android.content.Context;
import com.example.simplegolf.model.database.AppDatabase;
import androidx.room.Room;

/**
 * Singleton Repository
 */
public class Repository {
    private static Repository INSTANCE;
    private AppDatabase db;

    private Repository(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "golf-db")
                .fallbackToDestructiveMigration().build();
    }

    public static Repository getRepository(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }

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


// INSERTS / Completable
db.entityDAO().insert(...)
        .subscribeOn(Schedulers.io())
        .subscribe();

// SELECTS / LiveData
db.entityDAO().get(...).observe(this, data -> {
      // Do stuff here, Data is the callback variable.
});*/