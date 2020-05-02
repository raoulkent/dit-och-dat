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

    private List<Player> players = new ArrayList<>();


    public Scorecard(Course course) {
        this.course = course;
    }

    public void addPlayer(String name,String initials, Tee tee, double hcp) {
        players.add(new Player(name,initials, this.course, tee, hcp));
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
