package com.example.simplegolf.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Scorecard;

/**
 * Generates Database classes based on Room and handles all DB interactions
 */
@Database(entities = {Course.class, Scorecard.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();

    public abstract ScorecardDAO scorecardDAO();
}
