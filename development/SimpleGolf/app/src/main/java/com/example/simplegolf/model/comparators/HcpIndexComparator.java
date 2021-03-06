package com.example.simplegolf.model.comparators;

import com.example.simplegolf.model.Hole;

import java.util.Comparator;

/**
 * Can be used to sort a list of Hole by hcpIndex in ascending order
 */
public class HcpIndexComparator implements Comparator<Hole> {
    @Override
    public int compare(Hole hole1, Hole hole2) {
        if (hole1.getHcpIndex() < hole2.getHcpIndex()) {
            return -1;
        } else if (hole1.getHcpIndex() > hole2.getHcpIndex()) {
            return 1;
        }
        return 0;
    }
}
