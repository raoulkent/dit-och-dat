package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible of holding the data of the game
 */
public class Scorecard implements Serializable {
    private Course course;
    private String date;

    @Deprecated // Delete this vaiable when no longer used.
    private List<Hole> holes = new ArrayList<>();

    private List<Player> players = new ArrayList<>();

    @Deprecated // Delete this vaiable when no longer used.
    private int numberOfHoles;

    @Deprecated // Delete this constructor, use other constructor instead.
    public Scorecard(int numberOfHoles) {
        this.numberOfHoles = numberOfHoles;
        while (numberOfHoles > 0) {
            holes.add(new Hole(0,0));
            numberOfHoles--;
        }
    }

    public Scorecard(Course course) {
        this.course = course;
    }

    @Deprecated
    public void addPlayer(String name) {
        players.add(new Player(name, numberOfHoles));
    }

    public void addPlayer(String name, Tee tee, double hcp) {
        players.add(new Player(name, this.course, tee, hcp));
    }

    public List<Hole> getHoles() {
        return course.getHoles();
    }


    public int getNumberOfHoles() {
        return course.getHoles().size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Course getCourse() {
        return course;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }
}
