package com.example.simplegolf.model.database;

import com.example.simplegolf.model.Course;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;

@Dao
public interface CourseDAO {

    @Query("SELECT * FROM Course WHERE name = :name LIMIT 1")
    Course get(String name);

    @Query("SELECT * FROM Course order by name")
    List<Course> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);
}
