package com.example.simplegolf.model;

import com.example.simplegolf.model.testcourses.TestCourses;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ScorecardTest {

    private Player player;
    private Course course;
    private Tee tee;
    private Scorecard scorecard;

    @Before
    public void setUp() {
        scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        course = TestCourses.INSTANCE.getCourseChalmers();
        tee = course.getTees().get(2); // Gul tee
        player = new Player("Player1", "P1", course, tee, 36.0);
    }

    @Test
    public void testAddPlayer(){
        String expected = "P1";
        scorecard.addPlayer(player);
        String actual = scorecard.getPlayers().get(0).getInitials();
        assertEquals(actual, expected);
    }

    @Test
    public void testAddPlayer2(){
        //addPlayer(String name, String initials, Tee tee, double hcp)
        scorecard.addPlayer("Player2", "P2", tee, 36.0);
        String expected = "P2";
        String actual = scorecard.getPlayers().get(0).getInitials();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetHoles(){
        int expected = 18;
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        int actual = scorecard.getHoles().size();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetNumberOfHoles(){
        int expected = 18;
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        int actual = scorecard.getNumberOfHoles();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPlayers(){
        int expected = 0;
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        int actual = scorecard.getPlayers().size();
        assertEquals(actual,expected);
    }

    @Test
    public void testAddPlayers() {
        scorecard.addPlayer(player);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Player2", "P2", course, tee, 36.0));
        players.add(new Player("Player3", "P3", course, tee, 36.0));

        scorecard.addPlayers(players);
        List<Player> players2 = scorecard.getPlayers();

        assertEquals(3, players2.size());
        assertEquals("P1", players2.get(0).getInitials());
        assertEquals("P2", players2.get(1).getInitials());
        assertEquals("P3", players2.get(2).getInitials());
    }

    @Test
    public void testGetCourse() {
        assertEquals(TestCourses.INSTANCE.getCourseChalmers().getName(),
                scorecard.getCourse().getName());
    }

    @Test
    public void testSetAndGetDate() {
        String expected = "2020-05-18";
        scorecard.setDate(expected);
        assertEquals(expected, scorecard.getDate());
    }

    @Test
    public void testSetAndGetId() {
        long expected = 45;
        scorecard.setId(expected);
        assertEquals(expected, scorecard.getId());
    }

    @Test
    public void testSetCourse() {
        String expected = "TestCourse";
        Course course = new Course(expected, null, null);
        scorecard.setCourse(course);

        assertEquals(expected, scorecard.getCourse().getName());
    }

    @Test
    public void testIsFinishedCourse() {
        assertFalse(scorecard.isFinishedRound());
    }

    @Test
    public void testSetFinishedRound() {
        scorecard.setFinishedRound(true);
        assertTrue(scorecard.isFinishedRound());
    }

    @Test
    public void testSetPlayers() {
        scorecard.addPlayer(player);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Player2", "P2", course, tee, 36.0));
        players.add(new Player("Player3", "P3", course, tee, 36.0));

        scorecard.setPlayers(players);
        List<Player> players2 = scorecard.getPlayers();

        assertEquals(2, players2.size());
        assertEquals("P2", players2.get(0).getInitials());
        assertEquals("P3", players2.get(1).getInitials());
    }

}
