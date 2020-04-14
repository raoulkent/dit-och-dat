package com.example.simplegolf.model;

/**
 * This class is responsible of making a player
 */
 class Player {
   private String name ;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
