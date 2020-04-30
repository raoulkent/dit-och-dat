package com.example.simplegolf.model;

import java.io.Serializable;

/**
 * This class is responsible of holding the information about a hole
 */
public class Hole implements Serializable {
    private int holeNumber;
    private int hcpIndex;
    private int par;

    public Hole(int holeNumber, int par, int hcpIndex) {
        this.holeNumber = holeNumber;
        this.hcpIndex = hcpIndex;
        this.par = par;
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
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }
}
