package com.example.simplegolf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible of making a player
 */
 class Player {
   private String firstName ;
   private String lastName;
   private List<Integer> shots = new ArrayList<>();

    public Player(String firstName,String lastName ){

        this.firstName =firstName;
        this.lastName=lastName;
    }

    public String getFirstNameName(){
        return firstName;
    }
    public String getLastNameName(){
        return lastName;
    }

    public List<Integer>getShots(){
        return shots;
    }

    /**
     * This method give you the 3 first letters form the first name as Initials
     * @return A string Of 3 Letters .
     */
    public String getInitials(){
        return firstName.substring(0,3);
    }
}
