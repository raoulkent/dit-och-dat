package com.example.simplegolf.model.database;

import com.example.simplegolf.model.Course;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Course.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();
}
