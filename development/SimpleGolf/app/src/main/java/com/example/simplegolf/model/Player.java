package com.example.simplegolf.model;

import com.example.simplegolf.model.comparators.HcpIndexComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is responsible of making a player
 */
public class Player implements Serializable {
    private String initials; // 3 letter name
    private String name;
    private int[] shots;
    private Tee tee;
    private Course course;
    private double hcp; // -4.0 to 54.0


    /**
     * Deprecated: Use Player(String initials, Tee tee, Course course, double hcp) instead.
     * @param initials name
     * @param nHoles number of holes
     */
    @Deprecated // Delete constructor when it's no longer used.
    public Player(String initials, int nHoles) {
        this.initials = initials;
        this.shots = new int[nHoles];
    }

    public Player(String name, String initials, Course course, Tee tee, double hcp) {
        this.name = name;
        this.initials = initials;
        this.tee = tee;
        this.course = course;
        this.hcp = hcp;
        this.shots = new int[course.getHoles().size()];
    }

    /**
     * Calculates the "Spelhandicap" for the player
     * based on exact handicap, slope rating, course rating and course pars.
     * Formula: Spelhandicap = Exakthandicap * (Slopevärde / 113) + (Banvärde - Banans par)
     * @return Returns "Spelhandicap" in integer.
     */
    public int getShcp() {
        double shcp = hcp * (tee.getSlopeRating() / 113) + (tee.getCourseRating() - course.getTotalPar());
        return (int) Math.round(shcp);
    }


    public int[] getExtraShots() {
        // Distribute extra shots on each hole based on hcpIndex and schp.

        int[] extraShotsHole = new int[course.getHoles().size()];

        // todo this variable might need to be adjusted for 9-hole courses.
        int totalExtraShots = getShcp();

        // Make copy of list so we don't overwrite the original when sorting.
        List<Hole> holesHcpIndexOrdered = new ArrayList<>(course.getHoles());
        Collections.sort(holesHcpIndexOrdered, new HcpIndexComparator());

        while (totalExtraShots != 0) {
            for (int i = 0; i < holesHcpIndexOrdered.size(); i++) {
                if (totalExtraShots > 0) {
                    extraShotsHole[holesHcpIndexOrdered.get(i).getHoleNumber()]++;
                    totalExtraShots--;
                }
                else if (totalExtraShots < 0) {
                    extraShotsHole[holesHcpIndexOrdered.get(i).getHoleNumber()]--;
                    totalExtraShots++;
                }
                else {
                    return extraShotsHole;
                }
            }
        }
        return extraShotsHole;
    }

    /**
     * Calculates the current scores on each hole.
     * @return Returns an integer array that consists of current scores on each hole.
     */
    public int[] getScores() {
        List<Hole> holes = course.getHoles();

        int[] extraShotsHole = getExtraShots();
        // Calculate points for each hole given the extra shots from above.
        // Takes hcp and course into account.
        int[] score = new int[course.getHoles().size()];
        for (int i = 0; i < score.length; i++) {
            // Poäng = par + extra slag - slag + 2
            if (shots[i] == 0) {
                continue; // Don't count points for holes not played.
            }
            int points = holes.get(i).getPar() + extraShotsHole[i] - shots[i] + 2;
            if (points > 0) {
                score[i] = points;
            }
        }

        return score;
    }

    public int getPlayerPar(int holeNumber) {
        int[] extraShotsHole = getExtraShots();
        int par = course.getHoles().get(holeNumber).getPar();
        int playerPar = par + extraShotsHole[holeNumber];
        return playerPar;
    }

    /**
     * Calculates the current total score for the player.
     * @return Returns an integer with the current total score.
     */
    public int getTotalScore() {
        int totalScore = 0;
        for (int score : getScores()) {
            totalScore += score;
        }
        return totalScore;
    }

    public int getScoreForHole(int holeNumber) {
        return getScores()[holeNumber];
    }

    public String getInitials() {
        return initials;
    }

    public void setShotsForHole(int holeNumber, int shots) {
        this.shots[holeNumber] = shots;
    }

    public int getShotsForHole(int holeNumber) {
        return shots[holeNumber];
    }

    public int getTotalShots(){
        int sum = 0;
        for (int value : shots)
            sum += value;
        return sum;
    }

    public void incrementHole(int holeNumber){
        this.shots[holeNumber]++;
    }

    public void decrementHole(int holeNumber){
        if(this.shots[holeNumber]>0)
            this.shots[holeNumber]--;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName(){return name;}

    public int[] getShots() {
        return shots;
    }

    public void setShots(int[] shots) {
        this.shots = shots;
    }

    public Tee getTee() {
        return tee;
    }

    public void setTee(Tee tee) {
        this.tee = tee;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getHcp() {
        return hcp;
    }

    public void setHcp(double hcp) {
        this.hcp = hcp;
    }
}