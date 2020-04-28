package com.example.simplegolf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible of making a player
 */
public class Player implements Serializable {
    private String initials; // 3 letter name
    private int[] shots;
    private Tee tee;
    private Course course;
    private double hcp; // -4.0 to 54.0
    private int[] extraShotsHole = new int[18];


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

    public Player(String initials, Course course, Tee tee, double hcp) {
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

    /**
     * Calculates the current scores on each hole.
     * @return Returns an integer array that consists of current scores on each hole.
     */
    public int[] getScores() {
        // Todo add support for dynamic course size. (Currently only supports 18 holes).
        List<Hole> holes = course.getHoles();
        int totalPar = course.getTotalPar();
        int totalExtraShots = getShcp();


        // Distribute extra shots on each hole based on hcpIndex and schp.
        while (totalExtraShots != 0) {
            for (int i = 0; i < 18; i++) {
                if (totalExtraShots == 0) {
                    break;
                }
                for (int holeIndex = 0; holeIndex < 18; holeIndex++) {
                    if (holes.get(holeIndex).getHcpIndex() == i) {
                        if (totalExtraShots > 0) {
                            extraShotsHole[holeIndex]++;
                            totalExtraShots--;
                        }
                        else if (totalExtraShots < 0) {
                            extraShotsHole[holeIndex]--;
                            totalExtraShots++;
                        }
                    }
                }
            }
        }

        // Calculate points for each hole given the extra shots from above.
        // Takes hcp and course into account.
        int[] score = new int[18];
        for (int i = 0; i < 18; i++) {
            // Poäng = par + extra slag - slag + 2
            if (shots[i] == 0) {
                continue; // Don't count points for holes not played.
            }
            int points = holes.get(i).getPar() + extraShotsHole[i] - shots[i] + 2;
            if (points > 0) {
                score[i] = holes.get(i).getPar() + extraShotsHole[i] - shots[i] + 2;
            }
        }

        return score;
    }

    public int getStrokes(int holeNumber){
        List<Hole> holes = course.getHoles();
        return holes.get(holeNumber).getPar()+extraShotsHole[holeNumber];
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