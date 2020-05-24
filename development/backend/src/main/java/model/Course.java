package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private List<Hole> holes;
    private List<Tee> tees;

    public Course(String name, List<Hole> holes, List<Tee> tees) {
        this.name = name;
        this.holes = holes;
        this.tees = tees;
    }
}
