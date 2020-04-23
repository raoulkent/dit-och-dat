package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String name;
    private List<Hole> holes;
    private List<Tee> tees;

    public Course(String name,
                  List<Hole> holes,
                  List<Tee> tees) throws Exception {
        this.name = name;

        if (!checkCourseSize(holes)) {
            throw new Exception("Faulty course size");
        }

        if (!checkUniqueHoleHcpIndices(holes)) {
            throw new Exception("Duplicate handicap indices");
        }

        if (tees.isEmpty()) {
            throw new Exception("No tees for course");
        }

        this.holes = holes;
        this.tees = tees;
    }

    private static boolean checkUniqueHoleHcpIndices(List<Hole> holes) {
        List<Integer> foundHcps = new ArrayList<>();
        for (Hole hole : holes) {
            int hcp = hole.getHcp();
            if (foundHcps.contains(hcp)) {
                return false;
            }
            foundHcps.add(hcp);
        }
        return true;
    }

    private static boolean checkCourseSize(List<Hole> holes) {
        return holes.size() > 0 && holes.size() <= 18;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    public List<Tee> getTees() {
        return tees;
    }

    public void setTees(List<Tee> tees) {
        this.tees = tees;
    }
}