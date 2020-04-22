package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorecardTest {


    @Test
    public void testAddPlayer(){
        //Arrange
        Scorecard scorecard = new Scorecard(5);
        String expected = "Hus";

        //Act
        scorecard.addPlayer(expected);
        String actual = scorecard.getPlayers().get(0).getInitials();

        //Test
        assertEquals(actual,expected);

    }
    @Test
    public void testGetHoles(){
        //Arrange
        int expected = 5;
        Scorecard scorecard = new Scorecard(expected);

        //Act
        int actual = scorecard.getHoles().size();

        //Test
        assertEquals(actual,expected);

    }

    @Test
    public void testGetNumberOfHoles(){
        //Arrange
        int expected = 5;
        Scorecard scorecard = new Scorecard(expected);

        //Act
        int actual = scorecard.getNumberOfHoles();

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testGetPlayers(){
        //Arrange
        int expected = 0;
        Scorecard scorecard = new Scorecard(5);

        //Act
        int actual = scorecard.getPlayers().size();

        //Test
        assertEquals(actual,expected);
    }
}
