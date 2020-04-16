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

    public int getPar() {
        return par;
    }

    public void setPar(int a) {
        par = a;
    }

    public void setHcp(int a) {
        hcp = a;
    }

}
