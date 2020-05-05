package com.example.simplegolf.model;

import java.io.Serializable;

/**
 * Holds hole numner, par and hcpIndex.
 * hcpIndex is used to distribute extra shots for each player based on their shcp.
 */
public class Hole implements Serializable {
    private int holeNumber;
    private int par;
    private int hcpIndex;


    /**
     * Constructor for Hole
     *
     * @param holeNumber Ranges from 1 - 18
     * @param par        the assigned par for each hole
     * @param hcpIndex   Ranges from 1 - 18
     */
    public Hole(int holeNumber, int par, int hcpIndex) {
        this.holeNumber = holeNumber;
        this.par = par;
        this.hcpIndex = hcpIndex;
    }

    /**
     * Each hole stores a hcpIndex from 1 to 18 though for coherency with lists, -1 is applied
     *
     * @return hcpIndex - 1 (Ranging from 0 to 17)
     */
    public int getHcpIndex() {
        return hcpIndex - 1;
    }

    /**
     * Sets hcpIndex range from 1 to 18
     *
     * @param hcpIndex range must be 1 to 18
     */
    public void setHcpIndex(int hcpIndex) {
        this.hcpIndex = hcpIndex;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    /**
     * Each hole stores a hcpIndex from 1 to 18 though for coherency with lists, -1 is applied
     *
     * @return hcpIndex - 1 (Ranging from 0 to 17)
     */
    public int getHoleNumber() {
        return holeNumber - 1;
    }

    /**
     * Sets holeNumber, Must range from 1 to 18
     *
     * @param holeNumber range must be 1 to 18
     */
    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }
}
