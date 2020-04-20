package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.EnumMap;

public class Tee implements Serializable {
    private String name;
    private EnumMap<playerType, Double> ratings;

    public Tee(String name) {
        this.name = name;
        // TODO: Are ratings required to be set during constuction?
        //  How is this to be handled if the number of ratings is unknown?
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: Is there ever a need to only get _one_ rating?
    //  If so, then how do we ensure that we do not return a null value?
    public EnumMap<playerType, Double> getRatings() {
        return ratings;
    }

    public void setRating(playerType type, double rating) {
        ratings.put(type, rating);
    }

    public void setRatings(EnumMap<playerType, Double> ratings) {
        this.ratings = ratings;
    }
}

enum playerType {
    MALE,
    FEMALE,
    JUNIOR
}
