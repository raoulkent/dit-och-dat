package com.example.simplegolf.ui.playerselect;

import androidx.lifecycle.ViewModel;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.Tee;

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

    public void removePlayer(Player player) {
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

    public void editPlayer(Player player, String name, String abbr, Course course, Tee tee, double hcp) {
        if (this.players.contains(player)) {
            player.setName(name);
            player.setInitials(abbr);
            player.setCourse(course);
            player.setTee(tee);
            player.setHcp(hcp);
        }
    }
}