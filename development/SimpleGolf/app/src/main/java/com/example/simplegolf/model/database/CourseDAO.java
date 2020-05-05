package com.example.simplegolf.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.simplegolf.model.Course;

import java.util.List;

@Dao
public interface CourseDAO {

    /**
     * Gets a single course from the DB based on its unique name
     *
     * @param name The course name
     * @return a course
     */
    @Query("SELECT * FROM Course WHERE name = :name LIMIT 1")
    Course get(String name);

    /**
     * Gets all courses from the DB
     *
     * @return list of all courses in the DB
     */
    @Query("SELECT * FROM Course order by name")
    List<Course> getAll();

    /**
     * Saves a single course to the database
     *
     * @param course the course
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);

    /**
     * Deletes all courses in the DB. Does not affect saved Scorecards
     */
    @Query("DELETE FROM Course")
    void deleteAll();

    /**
     * Deletes a single course from the DB. Does not affect saved Scorecards
     *
     * @param course
     */
    @Delete
    void delete(Course course);
}
