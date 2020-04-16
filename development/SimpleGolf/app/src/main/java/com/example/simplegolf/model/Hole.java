package com.example.simplegolf.model;

/**
 * This class is responsible of holding the information about a hole
 */
 class Hole {
     private int hcp ;
     private int par;

    public Hole(){
        this.hcp=0;
        this.par=0;
    }

    public int getHcp(){
        return hcp;
    }
    public int getPar(){
        return par;
    }

    public void setPar(int a){
        par=a;
    }
    public void setHcp(int a){
        hcp=a;
    }

}
