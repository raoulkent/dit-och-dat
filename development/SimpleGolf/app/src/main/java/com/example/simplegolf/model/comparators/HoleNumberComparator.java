package com.example.simplegolf.model.comparators;

import com.example.simplegolf.model.Hole;

import java.util.Comparator;

/**
 * Can be used to sort a list of Hole by holeNumber in ascending order
 */
public class HoleNumberComparator implements Comparator<Hole> {

    @Override
    public int compare(Hole hole1, Hole hole2) {
        if (hole1.getHoleNumber() < hole2.getHoleNumber()) {
            return -1;
        } else if (hole1.getHoleNumber() > hole2.getHoleNumber()) {
            return 1;
        }
        return 0;
    }
}
