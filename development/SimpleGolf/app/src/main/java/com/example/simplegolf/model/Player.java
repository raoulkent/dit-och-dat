package com.example.simplegolf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible of making a player
 */
 class Player {
    private String initials;
    private  int [] shots ;

    public Player(String initials,int a ){
        this.initials=initials;
        this.shots=new int[a];
    }


    public int getShotsForHole(int i){
        return  shots[i];
    }


}
