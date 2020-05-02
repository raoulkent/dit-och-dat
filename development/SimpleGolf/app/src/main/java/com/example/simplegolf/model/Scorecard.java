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
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

/**
 * This class is responsible of holding the data of the game
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

    public void addPlayer(String name,String initials, Tee tee, double hcp) {
        players.add(new Player(name,initials, this.course, tee, hcp));
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
