package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testInitials(){
        //Arrange
        String expected = "HUS";
        Player player = new Player(expected ,0);

        //Act
        String actual= player.getInitials();

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testSetShotsShotsForHole(){
        //Arrange
        int expected = 10;
        Player player = new Player("HUS",2);

        //Act
        player.setShotsShotsForHole(0,10);
        int actual = player.getShotsForHole(0);

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testGetTotalShots(){
        //Arrange
        int expected = 20;
        Player player = new Player("HUS",2);

        //Act
        player.setShotsShotsForHole(0,10);
        player.setShotsShotsForHole(1,10);
        int actual = player.getTotalShots();

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testIncrementHole(){
        //Arrange
        Player player = new Player("HUS",2);
        int expected = 3;

        //Act
        player.setShotsShotsForHole(0,2);
        player.incrementHole(0);
        int actual = player.getShotsForHole(0);

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testDecrementHole(){
        //Arrange
        Player player = new Player("HUS",2);
        int expected = 1;

        //Act
        player.setShotsShotsForHole(0,2);
        player.decrementHole(0);
        int actual = player.getShotsForHole(0);

        //Test
        assertEquals(actual,expected);
    }
}
