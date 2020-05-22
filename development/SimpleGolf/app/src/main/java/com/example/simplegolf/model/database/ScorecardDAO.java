package com.example.simplegolf.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.simplegolf.model.Scorecard;

import java.util.List;

/**
 * This interface generates classes that manages the Scorecard in the database
 */
@Dao
public interface ScorecardDAO {

    /**
     * Inserts a scorecard into the DB
     *
     * @param scorecard the scorecard to insert
     * @return the auto generated id of the newly inserted scorecard
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Scorecard scorecard);

    /**
     * Gets all unfinished scorecards from the DB
     *
     * @return A list of all unfinished scorecards
     */
    @Query("SELECT * FROM Scorecard WHERE finishedRound = 0 ORDER BY id DESC")
    List<Scorecard> getUnfinishedRounds();

    /**
     * Gets all finished scorecards from the DB
     *
     * @return A list of all finished scorecards
     */
    @Query("SELECT * FROM Scorecard WHERE finishedRound = 1 ORDER BY id DESC")
    List<Scorecard> getFinishedRounds();

    /**
     * Updates an existing scorecard in the DB, such as an ongoing round
     *
     * @param scorecard the updated scorecard to save
     */
    @Update
    void update(Scorecard scorecard);

    /**
     * Deletes a single scorecard in the DB
     *
     * @param scorecard the scorecard to delete
     */
    @Delete
    void delete(Scorecard scorecard);

    /**
     * Deletes all scorecards in the DB
     */
    @Query("DELETE From Scorecard")
    void deleteAll();

    /**
     * Gets a single scorecard from the DB based on its ID
     *
     * @param id the id of the scorecard
     * @return a scorecard
     */
    @Query("SELECT * From Scorecard WHERE id = :id")
    Scorecard getById(long id);

    /**
     * Deletes all finished scorecards
     */
    @Query("DELETE FROM Scorecard WHERE finishedRound = 1")
    void deleteAllFinished();

    /**
     * Deletes all unfinished scorecards
     */
    @Query("DELETE FROM Scorecard WHERE finishedRound = 0")
    void deleteAllUnfinished();
}
