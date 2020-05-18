package com.example.simplegolf.model;

import com.example.simplegolf.model.testcourses.TestCourses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerTest {

    private Course course;
    private Tee tee;
    private Player player;

    @Before
    public void setUp() {
        course = TestCourses.INSTANCE.getCourseChalmers();
        tee = course.getTees().get(2); // Gul tee
        player = new Player("Player1", "P1", course, tee, 36.0);
    }

    @Test
    public void testGetInitials() {
        assertEquals(player.getInitials(), "P1");
    }

    @Test
    public void testSetShotsForHole(){
        int expected = 10;
        player.setShotsForHole(0,10);
        int actual = player.getShotsForHole(0);
        assertEquals(actual,expected);
    }

    @Test
    public void testGetTotalShots(){
        int expected = 20;
        player.setShotsForHole(0,10);
        player.setShotsForHole(1,10);
        int actual = player.getTotalShots();
        assertEquals(actual,expected);
    }

    @Test
    public void testIncrementHole(){
        int expected = 3;
        player.setShotsForHole(0,2);
        player.incrementHole(0);
        int actual = player.getShotsForHole(0);
        assertEquals(actual,expected);
    }

    @Test
    public void testDecrementHole(){
        int expected = 1;
        player.setShotsForHole(0,2);
        player.decrementHole(0);
        int actual = player.getShotsForHole(0);
        assertEquals(actual,expected);
    }

    @Test
    public void testGetShcpYellowTee() {
        int expectedShcp = 39;
        int shcp = player.getShcp();
        assertEquals(expectedShcp, shcp);
    }

    @Test
    public void testGetShcpRedTee() {
        int expectedShcp = 33;
        Tee redTee = course.getTees().get(0);
        player.setTee(redTee);
        int shcp = player.getShcp();
        assertEquals(expectedShcp, shcp);
    }

    @Test
    public void testGetShcpWhiteTee() {
        int expectedShcp = 40;
        Tee whiteTee = course.getTees().get(3);
        player.setTee(whiteTee);
        int shcp = player.getShcp();
        assertEquals(expectedShcp, shcp);
    }

    @Test
    public void testGetShcpBlueTee() {
        int expectedShcp = 35;
        Tee blueTee = course.getTees().get(1);
        player.setTee(blueTee);
        int shcp = player.getShcp();
        assertEquals(expectedShcp, shcp);
    }

    @Test
    public void testWhiteTeeNegativeHcp() {
        int expectedShcp = -5;
        Tee whiteTee = course.getTees().get(3);
        player.setTee(whiteTee);
        player.setHcp(-4);
        int shcp = player.getShcp();
        assertEquals(expectedShcp, shcp);
    }

    @Test
    public void testGetScoresZero() {
        for (int i : player.getScores()) {
            assertEquals(0, i);
        }
    }

    @Test
    public void testGetScores() {
        for (int i = 0; i < course.getHoles().size(); i++) {
            player.setShotsForHole(i, course.getHoles().get(i).getPar());
        }
        int[] scores = player.getScores();
        assertEquals(4, scores[0]);
        assertEquals(4, scores[1]);
        assertEquals(4, scores[2]);
        assertEquals(5, scores[11]);
        assertEquals(5, scores[5]);
        assertEquals(5, scores[16]);
    }


    @Test
    public void testGetScoresPlusHcp() {
        player.setHcp(-1);
        for (int i = 0; i < course.getHoles().size(); i++) {
            player.setShotsForHole(i, course.getHoles().get(i).getPar());
        }
        int[] scores = player.getScores();

        assertEquals(2, scores[0]);
        assertEquals(2, scores[1]);
        assertEquals(2, scores[2]);
        assertEquals(1, scores[11]);
        assertEquals(1, scores[5]);
        assertEquals(2, scores[16]);
    }

    @Test
    public void testGetTotalScore() {
        for (int i = 0; i < course.getHoles().size(); i++) {
            player.setShotsForHole(i, course.getHoles().get(i).getPar());
        }
        assertEquals(75, player.getTotalScore());
    }

    @Test
    public void testGetScoreForHole() {
        int holeNumber = 2; // par 4 hole
        int shots = 6;
        player.setShotsForHole(holeNumber, shots);
        int expected = 2;
        assertEquals(expected, player.getScoreForHole(holeNumber));

        for (int i = 0; i < course.getHoles().size(); i++) {
            player.setShotsForHole(i, course.getHoles().get(i).getPar());
        }
        expected = 4;
        assertEquals(expected, player.getScoreForHole(1));
    }

    @Test
    public void testGetShotsForHole() {
        int shots = 6;
        int holeNumber = 2;
        player.setShotsForHole(holeNumber,shots);
        assertEquals(shots, player.getShotsForHole(holeNumber));
    }

    @Test
    public void testSetInitials() {
        String s = "abc";
        player.setInitials(s);
        assertEquals(s, player.getInitials());
    }

    @Test
    public void testGetShots() {
        int[] shots = {5, 4};
        player.setShots(shots);
        assertEquals(shots, player.getShots());
    }

    @Test
    public void testSetShots() {
        int[] shots = {5, 4};
        player.setShots(shots);
        assertEquals(shots, player.getShots());
    }

    @Test
    public void testGetTee() {
        assertEquals(tee, player.getTee());
    }

    @Test
    public void testSetTee() {
        Tee tee = new Tee("Röd", 22.0, 34);
        player.setTee(tee);
        assertEquals(tee, player.getTee());
    }

    @Test
    public void testGetCourse() {
        assertEquals(course, player.getCourse());
    }

    @Test
    public void testSetCourse() {
        player.setCourse(course);
        assertEquals(course, player.getCourse());
    }

    @Test
    public void testGetHcp() {
        double expected = 36.0;
        double delta = 0.0;
        assertEquals(expected, player.getHcp(), delta);
    }

    @Test
    public void testSetHcp() {
        double expected = 14.3;
        double delta = 0.0;
        player.setHcp(expected);
        assertEquals(expected, player.getHcp(), delta);
    }


    @Test
    public void testGetExtraShots() {
        int[] extraShots = player.getExtraShots();

        assertEquals(2, extraShots[0]);
        assertEquals(2, extraShots[1]);
        assertEquals(3, extraShots[11]);
        assertEquals(2, extraShots[9]);
    }

    @Test
    public void testGetExtraShotsForScratchPlayer() {
        player.setHcp(0);
        player.setTee(course.getTees().get(3)); // Vit tee = 0 SHCP

        int[] extraShots = player.getExtraShots();

        assertEquals(0, extraShots[0]);
        assertEquals(0, extraShots[1]);
        assertEquals(0, extraShots[11]);
        assertEquals(0, extraShots[9]);

    }

    @Test
    public void testGetPlayerPar() {
        assertEquals(6, player.getPlayerPar(0));
        assertEquals(5, player.getPlayerPar(1));
        assertEquals(6, player.getPlayerPar(2));
        assertEquals(6, player.getPlayerPar(9));
        assertEquals(8, player.getPlayerPar(11));
    }

    @Test
    public void testGetName() {
        assertEquals("Player1", player.getName());
    }

    @Test
    public void testEmptyConstructor() {
        Player p = new Player();
        assertNotNull(p);
    }

    // TODO Need tests for 9-hole courses?


}
