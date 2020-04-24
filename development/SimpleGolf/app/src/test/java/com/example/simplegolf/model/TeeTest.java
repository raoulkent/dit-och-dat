package com.example.simplegolf.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeeTest {
    Tee tee;

    @Before
    public void setUp() throws Exception {
        try {
            tee = new Tee("Blue", 50.0, 60.0);
        }
        catch (Exception e) {
            System.out.println("Could not construct a Tee");
        }
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Blue", tee.getName());
    }

    @Test
    public void testSetName() {
        tee.setName("Red");
        Assert.assertEquals("Red", tee.getName());
    }

    @Test
    public void getMensRating() {
        Assert.assertEquals(50.0, tee.getCourseRating(),0.01);
    }

    @Test
    public void setMensRating() {
        tee.setCourseRating(83.6);
        Assert.assertEquals(83.6, tee.getCourseRating(), 0.01);
    }

    @Test
    public void getWomensRating() {
        Assert.assertEquals(60.0, tee.getSlopeRating(),0.01);
    }

    @Test
    public void setWomensRating() {
        tee.setSlopeRating(93.6);
        Assert.assertEquals(93.6, tee.getSlopeRating(), 0.01);
    }
}