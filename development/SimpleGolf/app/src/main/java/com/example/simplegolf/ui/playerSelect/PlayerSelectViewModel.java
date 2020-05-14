package com.example.simplegolf.ui.playerSelect;

import androidx.lifecycle.ViewModel;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectViewModel extends ViewModel {
    private List<Player> players = new ArrayList<>();
    private Course course;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void deletePlayer(Player player) {
        players.remove(player);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Scorecard buildScorecard() {
        Scorecard scorecard = new Scorecard(this.course);
        scorecard.addPlayers(this.players);
        return scorecard;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}