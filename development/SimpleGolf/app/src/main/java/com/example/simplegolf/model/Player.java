package com.example.simplegolf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible of making a player
 */
class Player {
    private String initials;
    private int[] shots;

    public Player(String initials, int a) {
        this.initials = initials;
        this.shots = new int[a];
    }

    public String getInitials() {
        return initials;
    }

//  TODO: Enable this if there's a need to change initials
//    public void setInitials(String initials) {
//        this.initials = initials;
//    }

    public void setShotsShotsForHole(int holeNumber, int shots) {
        this.shots[holeNumber] = shots;
    }

    public int getShotsForHole(int holeNumber) {
        return shots[holeNumber];
    }
}