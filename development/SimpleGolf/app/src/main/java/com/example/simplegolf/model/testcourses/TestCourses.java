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
     * Generates test courses
     */
    TestCourses() {

        // Chalmers real 18 hole course
        List<Hole> holes = new ArrayList<>();
        List<Tee> tees = new ArrayList<>();

        holes.add(new Hole(1, 4, 6));
        holes.add(new Hole(2, 3, 18));
        holes.add(new Hole(3, 4, 16));
        holes.add(new Hole(4, 5, 10));
        holes.add(new Hole(5, 4, 12));
        holes.add(new Hole(6, 5, 2));
        holes.add(new Hole(7, 3, 4));
        holes.add(new Hole(8, 4, 8));
        holes.add(new Hole(9, 3, 14));

        holes.add(new Hole(10, 4, 9));
        holes.add(new Hole(11, 3, 17));
        holes.add(new Hole(12, 5, 1));
        holes.add(new Hole(13, 4, 11));
        holes.add(new Hole(14, 4, 13));
        holes.add(new Hole(15, 3, 5));
        holes.add(new Hole(16, 4, 15));
        holes.add(new Hole(17, 5, 3));
        holes.add(new Hole(18, 4, 7));

        tees.add(new Tee("Röd - Herrar", 66.1, 118));
        tees.add(new Tee("Blå - Herrar", 67.4, 120));
        tees.add(new Tee("Gul - Herrar", 69.9, 126));
        tees.add(new Tee("Vit - Herrar", 70.8, 127));

        tees.add(new Tee("Röd - Damer", 71.6, 122));
        tees.add(new Tee("Blå - Damer", 73.2, 125));
        tees.add(new Tee("Gul - Damer", 76.3, 132));
        try {
            courseChalmers = new Course("Chalmers GK - 18 hål", holes, tees);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Course getCourseChalmers() {
        return courseChalmers;
    }

}
