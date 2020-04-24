package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testInitials(){
        String expected = "HUS";
        Player player = new Player(expected ,0);
        String actual= player.getInitials();
        assertEquals(actual,expected);
    }

    @Test
    public void testSetShotsShotsForHole(){
        int expected = 10;
        Player player = new Player("HUS",2);
        player.setShotsShotsForHole(0,10);
        int actual = player.getShotsForHole(0);
        assertEquals(actual,expected);
    }

    @Test
    public void testGetTotalShots(){
        int expected = 20;
        Player player = new Player("HUS",2);
        player.setShotsShotsForHole(0,10);
        player.setShotsShotsForHole(1,10);
        int actual = player.getTotalShots();
        assertEquals(actual,expected);
    }

    @Test
    public void testIncrementHole(){
        Player player = new Player("HUS",2);
        int expected = 3;
        player.setShotsShotsForHole(0,2);
        player.incrementHole(0);
        int actual = player.getShotsForHole(0);
        assertEquals(actual,expected);
    }

    @Test
    public void testDecrementHole(){
        Player player = new Player("HUS",2);
        int expected = 1;
        player.setShotsShotsForHole(0,2);
        player.decrementHole(0);
        int actual = player.getShotsForHole(0);
        assertEquals(actual,expected);
    }
}
