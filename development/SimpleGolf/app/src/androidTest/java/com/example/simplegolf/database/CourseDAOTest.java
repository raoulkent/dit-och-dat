package com.example.simplegolf.database;

import android.content.Context;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.database.AppDatabase;
import com.example.simplegolf.model.database.CourseDAO;
import com.example.simplegolf.model.database.ScorecardDAO;
import com.example.simplegolf.model.testcourses.TestCourses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CourseDAOTest {
    private CourseDAO courseDAO;
    private AppDatabase db;
    private Course course;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        courseDAO = db.courseDAO();
        course = TestCourses.INSTANCE.getCourseChalmers();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertAndGetAll() {
        courseDAO.insert(course);
        course.setName("Test");
        courseDAO.insert(course);
        List<Course> courses = courseDAO.getAll();
        assertEquals(2, courses.size());
        assertEquals(course.getName(), courses.get(1).getName());
    }

    @Test
    public void testGet() {
        String expected = course.getName();
        courseDAO.insert(course);
        course.setName("Test");
        courseDAO.insert(course);
        Course result = courseDAO.get(expected);
        assertEquals(expected, result.getName());
    }

    @Test
    public void testDeleteAll() {
        courseDAO.insert(course);
        course.setName("Test");
        courseDAO.insert(course);
        List<Course> courses = courseDAO.getAll();
        assertEquals(2, courses.size());
        courseDAO.deleteAll();
        courses = courseDAO.getAll();
        assertEquals(0, courses.size());
    }

    @Test
    public void testDelete() {
        courseDAO.insert(course);
        course.setName("Test");
        courseDAO.insert(course);
        List<Course> courses = courseDAO.getAll();
        assertEquals(2, courses.size());
        courseDAO.delete(courses.get(0));
        courses = courseDAO.getAll();
        assertEquals(1, courses.size());
    }

}
