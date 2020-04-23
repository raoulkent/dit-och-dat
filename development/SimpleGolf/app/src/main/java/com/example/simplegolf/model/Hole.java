package com.example.simplegolf.model;

import java.io.Serializable;

/**
 * This class is responsible of holding the information about a hole
 */
public class Hole implements Serializable {
    private int hcp;
    private int par;

    public Hole(int hcp, int par) {
        this.hcp = hcp;
        this.par = par;
    }

    public int getHcp() {
        return hcp;
    }

    public void setHcp(int hcp) {
        this.hcp = hcp;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }
}
