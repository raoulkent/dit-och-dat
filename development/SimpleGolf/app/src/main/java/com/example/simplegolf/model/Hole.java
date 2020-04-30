package com.example.simplegolf.model;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class is responsible of holding the information about a hole
 */

public class Hole implements Serializable {

    private String course;
    private int holeNumber;
    private int par;
    private int hcpIndex;

    public Hole() {
    }

    public Hole(int holeNumber, int par, int hcpIndex) {
        this.holeNumber = holeNumber;
        this.par = par;
        this.hcpIndex = hcpIndex;
    }

    public int getHcpIndex() {
        return hcpIndex - 1;
    }

    public void setHcpIndex(int hcpIndex) {
        this.hcpIndex = hcpIndex;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getHoleNumber() {
        return holeNumber - 1;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    @Override
    public String toString() {
        return "Hole{" +
                "holeNumber=" + holeNumber +
                ", hcpIndex=" + getHcpIndex() +
                ", par=" + par +
                '}';
    }

}
