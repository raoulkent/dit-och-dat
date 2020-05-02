package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

/**
 * This class is responsible of holding the data of the game
 */
@Entity
public class Scorecard implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Embedded
    private Course course;
    private String date;
    private boolean finishedRound = false;


    @Ignore
    private List<Player> players = new ArrayList<>();


    public Scorecard(Course course) {
        this.course = course;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isFinishedRound() {
        return finishedRound;
    }

    public void setFinishedRound(boolean finishedRound) {
        this.finishedRound = finishedRound;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
