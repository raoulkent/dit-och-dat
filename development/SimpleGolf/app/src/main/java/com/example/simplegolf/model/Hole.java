package com.example.simplegolf.model;

/**
 * This class is responsible of holding the information about a hole
 */
 class Hole {
     private int shots ;

    public Hole(){
        this.shots=0;
    }

    public int getShots(){
        return shots;
    }

    public void setShots(int a){
        shots=a;
    }

    public void increaseShot(){
        shots++;
    }
}
