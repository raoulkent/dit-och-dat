package com.example.simplegolf.model.database;

import com.example.simplegolf.model.Scorecard;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import io.reactivex.Completable;

@Dao
public interface ScorecardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Scorecard scorecard);
}
