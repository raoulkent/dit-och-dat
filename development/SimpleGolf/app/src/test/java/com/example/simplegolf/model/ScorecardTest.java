package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorecardTest {


    @Test
    public void testAddPlayer(){
        Scorecard scorecard = new Scorecard(5);
        String expected = "Hus";
        scorecard.addPlayer(expected);
        String actual = scorecard.getPlayers().get(0).getInitials();
        assertEquals(actual,expected);

    }
    @Test
    public void testGetHoles(){
        int expected = 5;
        Scorecard scorecard = new Scorecard(expected);
        int actual = scorecard.getHoles().size();
        assertEquals(actual,expected);

    }

    @Test
    public void testGetNumberOfHoles(){
        int expected = 5;
        Scorecard scorecard = new Scorecard(expected);
        int actual = scorecard.getNumberOfHoles();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetPlayers(){
        int expected = 0;
        Scorecard scorecard = new Scorecard(5);
        int actual = scorecard.getPlayers().size();
        assertEquals(actual,expected);
    }
}
