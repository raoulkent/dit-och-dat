package com.example.simplegolf.model.testcourses;

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
    private Course courseAlingsas;
    private Course courseOijared;

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
            courseChalmers = new Course("Chalmers GK", holes, tees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Alingsås real 18 hole course
        List<Hole> holes1 = new ArrayList<>();
        List<Tee> tees1 = new ArrayList<>();

        holes1.add(new Hole(1, 5, 11));
        holes1.add(new Hole(2, 4, 3));
        holes1.add(new Hole(3, 3, 7));
        holes1.add(new Hole(4, 5, 1));
        holes1.add(new Hole(5, 4, 15));
        holes1.add(new Hole(6, 3, 17));
        holes1.add(new Hole(7, 4, 13));
        holes1.add(new Hole(8, 4, 5));
        holes1.add(new Hole(9, 4, 9));

        holes1.add(new Hole(10, 4, 10));
        holes1.add(new Hole(11, 5, 4));
        holes1.add(new Hole(12, 4, 18));
        holes1.add(new Hole(13, 4, 6));
        holes1.add(new Hole(14, 4, 2));
        holes1.add(new Hole(15, 3, 14));
        holes1.add(new Hole(16, 4, 16));
        holes1.add(new Hole(17, 3, 8));
        holes1.add(new Hole(18, 5, 12));

        tees1.add(new Tee("Röd - Herrar", 66.4, 126));
        tees1.add(new Tee("Gul - Herrar", 71, 135));


        tees1.add(new Tee("Röd - Damer", 70.9, 126));
        tees1.add(new Tee("Gul - Damer", 76.7, 136));
        try {
            courseAlingsas = new Course("Alingsås GK", holes, tees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Chalmers real 18 hole course
        List<Hole> holes2 = new ArrayList<>();
        List<Tee> tees2 = new ArrayList<>();

        holes2.add(new Hole(1, 5, 9));
        holes2.add(new Hole(2, 4, 13));
        holes2.add(new Hole(3, 3, 7));
        holes2.add(new Hole(4, 4, 3));
        holes2.add(new Hole(5, 4, 1));
        holes2.add(new Hole(6, 5, 15));
        holes2.add(new Hole(7, 4, 5));
        holes2.add(new Hole(8, 3, 17));
        holes2.add(new Hole(9, 4, 11));

        holes2.add(new Hole(10, 4, 10));
        holes2.add(new Hole(11, 5, 16));
        holes2.add(new Hole(12, 3, 18));
        holes2.add(new Hole(13, 4, 6));
        holes2.add(new Hole(14, 4, 8));
        holes2.add(new Hole(15, 4, 2));
        holes2.add(new Hole(16, 3, 14));
        holes2.add(new Hole(17, 5, 4));
        holes2.add(new Hole(18, 4, 12));

        tees2.add(new Tee("Orange - Herrar", 69.4, 126));
        tees2.add(new Tee("Röd - Herrar", 71.5, 131));
        tees2.add(new Tee("Gul - Herrar", 73.2, 134));
        tees2.add(new Tee("Vit - Herrar", 74.7, 137));

        tees2.add(new Tee("Orange - Damer", 75.4, 130));
        tees2.add(new Tee("Röd - Damer", 78, 135));
        tees2.add(new Tee("Gul - Damer", 80.1, 139));
        tees2.add(new Tee("Vit - Damer", 80.1, 139));
        try {
            courseOijared = new Course("Öijared GK Gamla banan", holes, tees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets a fully working course based on Chalmers GK - 18 hole course
     *
     * @return chalmers gk - 18 hole course
     */
    public Course getCourseOijared(){return courseOijared;}
    public Course getCourseAlingsas(){ return  courseAlingsas;}
    public Course getCourseChalmers() {
        return courseChalmers;
    }

}
