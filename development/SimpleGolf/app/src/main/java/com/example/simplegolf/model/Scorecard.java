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

    public Scorecard(int numberOfHoles) {
        while (numberOfHoles > 0) {
            holes.add(new Hole());
            numberOfHoles--;
        }
    }

    public void addPLayerToList(Player p) {
        players.add(p);
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public int getNumberOfHoles() {
        return holes.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
