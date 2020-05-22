package com.example.simplegolf.ui.playerselect;

import androidx.lifecycle.ViewModel;

import com.example.simplegolf.model.Course;
import com.example.simplegolf.model.Player;
import com.example.simplegolf.model.Scorecard;
import com.example.simplegolf.model.Tee;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectViewModel extends ViewModel {
    private ArrayList<PlayerSelectListener> listeners = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Course course;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyListeners();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        notifyListeners();
    }

    public void removePlayer(Player player) {
        players.remove(player);
        notifyListeners();
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

    public void editPlayer(Player player, String name, String abbr, Course course, Tee tee, double hcp) {
        if (this.players.contains(player)) {
            player.setName(name);
            player.setInitials(abbr);
            player.setCourse(course);
            player.setTee(tee);
            player.setHcp(hcp);
        }
        notifyListeners();
    }

    public void subscribeAsListener(PlayerSelectListener listener) {
        this.listeners.add(listener);
    }

    public void unsubscribeAsListener(PlayerSelectListener listener) {
        this.listeners.remove(listener);
    }

    private void notifyListeners() {
        for (PlayerSelectListener listener : listeners) {
            listener.playersChanged();
        }
    }
}

