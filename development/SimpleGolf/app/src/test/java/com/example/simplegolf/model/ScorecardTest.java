package com.example.simplegolf.model;

import com.example.simplegolf.model.testcourses.TestCourses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorecardTest {

    private Player player;
    private Course course;
    private Tee tee;

    @Before
    public void setUp() {
        course = TestCourses.INSTANCE.getCourseChalmers();
        tee = course.getTees().get(2); // Gul tee
        player = new Player("Player1", "P1", course, tee, 36.0);
    }

    @Test
    public void testAddPlayer(){
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        String expected = "P1";
        scorecard.addPlayer(player);
        String actual = scorecard.getPlayers().get(0).getInitials();
        assertEquals(actual, expected);

    }
    @Test
    public void testGetHoles(){
        int expected = 5;
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        int actual = scorecard.getHoles().size();
        assertEquals(actual,expected);

    }

    @Test
    public void testGetNumberOfHoles(){
        int expected = 5;
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        int actual = scorecard.getNumberOfHoles();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetPlayers(){
        int expected = 0;
        Scorecard scorecard = new Scorecard(TestCourses.INSTANCE.getCourseChalmers());
        int actual = scorecard.getPlayers().size();
        assertEquals(actual,expected);
    }
}
