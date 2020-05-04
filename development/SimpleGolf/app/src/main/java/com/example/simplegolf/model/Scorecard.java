package com.example.simplegolf.model;

import android.util.Log;

import com.example.simplegolf.model.converters.PlayerConverter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * Scorecard represents each round of golf and contains player and course data.
 */
@Entity
public class Scorecard implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @Embedded
    private Course course;
    private String date;
    private boolean finishedRound = false;

    @TypeConverters(PlayerConverter.class)
    private List<Player> players = new ArrayList<>();


    public Scorecard(Course course) {
        this.course = course;
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("sv_SE"));
        date = sdf.format(today);
    }

    /**
     * Adds a player to the scorecard
     * @param name Player's name
     * @param initials Player's initials
     * @param tee The chosen Tee of the player
     * @param hcp The individual handicap of the player
     */
    public void addPlayer(String name,String initials, Tee tee, double hcp) {
        players.add(new Player(name,initials, this.course, tee, hcp));
    }

    /**
     * Adds a player to the scorecard
     * @param player Make sure that player contains Course & Tee
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Adds multiple players to the scorecard.
     * @param players Make sure that each player contains Course & Tee
     */
    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
    }


    /**
     * Gets all holes from the connected Course
     * @return List of holes from the connected Course
     */
    public List<Hole> getHoles() {
        return course.getHoles();
    }


    /**
     * Gets the total number of holes from the connected course.
     * @return Number of holes from the connected course.
     */
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * When the round is finished / locked, this returns true. Decides which view it should
     * be displayed in.
     * @return Returns true if round is finished, otherwise false.
     */
    public boolean isFinishedRound() {
        return finishedRound;
    }

    /**
     * Sets the status of this scorecard, decides which view it will be displayed in.
     * @param finishedRound true if finished, false if not finished
     */
    public void setFinishedRound(boolean finishedRound) {
        this.finishedRound = finishedRound;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
