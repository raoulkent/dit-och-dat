package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible of holding the data of the game
 */
public class Scorecard implements Serializable {
    private ArrayList<Hole> holes = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private int numberOfHoles;

    public Scorecard(int numberOfHoles) {
        this.numberOfHoles = numberOfHoles;
        while (numberOfHoles > 0) {
            holes.add(new Hole());
            numberOfHoles--;
        }
    }

    public void addPlayer(String name) {
        players.add(new Player(name, numberOfHoles));
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public int getNumberOfHoles() {
        return numberOfHoles;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
