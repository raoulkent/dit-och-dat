package com.example.simplegolf.model;

import java.io.Serializable;


public class Tee implements Serializable {
    private String name;
    private double courseRating;
    private double slopeRating;

    public Tee(String name, double courseRating, double slopeRating) {
        this.name = name;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }


}