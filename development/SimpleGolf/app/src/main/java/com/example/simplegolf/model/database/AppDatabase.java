package com.example.simplegolf.model.database;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Hole;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Course.class, Player.class, Scorecard.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract PlayerDAO playerDAO();
    public abstract ScorecardDAO scorecardDAO();
}
