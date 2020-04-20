package com.example.simplegolf.model;

import java.io.Serializable;

public class Tee implements Serializable {
    private String name;
    private double mensRating, womensRating;

    public Tee(String name, double mensRating, double womensRating) {
        this.name = name;
        this.mensRating = mensRating;
        this.womensRating = womensRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMensRating() {
        return mensRating;
    }

    public void setMensRating(double mensRating) {
        this.mensRating = mensRating;
    }

    public double getWomensRating() {
        return womensRating;
    }

    public void setWomensRating(double womensRating) {
        this.womensRating = womensRating;
    }
}