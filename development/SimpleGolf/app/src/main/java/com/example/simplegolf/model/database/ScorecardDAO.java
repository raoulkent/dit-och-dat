package com.example.simplegolf.model.database;

import com.example.simplegolf.model.Scorecard;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;

@Dao
public interface ScorecardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Scorecard scorecard);

    @Query("SELECT * FROM Scorecard WHERE finishedRound = 0")
    List<Scorecard> getUnfinishedRounds();

    @Query("SELECT * FROM Scorecard WHERE finishedRound = 1 ")
    List<Scorecard> getFinishedRounds();

    @Update
    void update(Scorecard scorecard);

    @Delete
    void delete(Scorecard scorecard);

    @Query("DELETE From Scorecard")
    void deleteAll();
}