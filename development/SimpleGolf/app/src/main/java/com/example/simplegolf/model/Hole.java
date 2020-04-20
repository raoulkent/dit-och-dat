package com.example.simplegolf.model;

import java.io.Serializable;

/**
 * This class is responsible of holding the information about a hole
 */
class Hole implements Serializable {
    private int hcp;
    private int par;

    Hole() {
        this.hcp = 0;
        this.par = 0;
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
