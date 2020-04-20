package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String name;
    private double courseRating;
    private List<Hole> holes;
    private List<Tee> tees;

    public Course(String name,
                  double courseRating,
                  List<Hole> holes,
                  List<Tee> tees) {
        this.name = name;
        this.courseRating = courseRating;

        // TODO: Ensure that holes.size is in (1,18)
        //  use checkCourseSize

        // TODO: Ensure that holes only has one of each hcp
        //  use checkUniqueHoleHcpIndices!

        this.holes = holes;
        this.tees = tees;
    }

    public static boolean checkUniqueHoleHcpIndices(List<Hole> holes) {
        List<Integer> foundHcps = new ArrayList<>();
        for (Hole hole : holes) {
            int hcp = hole.getHcp();
            if (foundHcps.contains(hcp)) { return false; }
            foundHcps.add(hcp);
        }
        return true;
    }

    public static boolean checkCourseSize(List<Hole> holes) {
        return holes.size() > 0 && holes.size() <= 18;
    }
}