package com.example.simplegolf.model.database;

import com.example.simplegolf.model.Player;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;

@Dao
public interface PlayerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Player player);

    @Query("SELECT * FROM Player WHERE id = :id LIMIT 1")
    LiveData<Player> get(int id);
}
