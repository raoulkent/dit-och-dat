package com.example.simplegolf.model.testcourses;

import android.util.Log;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Hole;
import com.example.simplegolf.model.Tee;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that contains hardcoded test courses.
 * Use get methods to get a constructed course.
 */
public enum TestCourses {
    INSTANCE;

    private Course courseChalmers;

    /**
     * Generates courses
     */
    TestCourses() {
        List<Hole> holes = new ArrayList<>();
        List<Tee> tees = new ArrayList<>();
        holes.add(new Hole(4, 6));
        holes.add(new Hole(3, 18));
        holes.add(new Hole(4, 16));
        holes.add(new Hole(5, 10));
        holes.add(new Hole(4, 12));
        holes.add(new Hole(5, 2));
        holes.add(new Hole(3, 4));
        holes.add(new Hole(4, 8));
        holes.add(new Hole(3, 14));

        holes.add(new Hole(4, 9));
        holes.add(new Hole(3, 17));
        holes.add(new Hole(5, 1));
        holes.add(new Hole(4, 11));
        holes.add(new Hole(4, 13));
        holes.add(new Hole(3, 5));
        holes.add(new Hole(4, 15));
        holes.add(new Hole(5, 3));
        holes.add(new Hole(4, 7));

        tees.add(new Tee("Röd - Herrar", 66.1, 118));
        tees.add(new Tee("Blå - Herrar", 67.4, 120));
        tees.add(new Tee("Gul - Herrar", 69.9, 126));
        tees.add(new Tee("Vit - Herrar", 70.8, 127));

        tees.add(new Tee("Röd - Damer", 71.6, 122));
        tees.add(new Tee("Blå - Damer", 73.2, 125));
        tees.add(new Tee("Gul - Damer", 76.3, 132));
        try {
            courseChalmers = new Course("Chalmers GK", holes, tees);
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }

    public Course getCourseChalmers() {
        return courseChalmers;
    }
}