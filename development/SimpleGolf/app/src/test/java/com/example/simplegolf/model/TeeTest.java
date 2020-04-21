package com.example.simplegolf.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeeTest {
    Tee tee;

    @Before
    public void setUp() throws Exception {
        tee = new Tee("Blue", 50.0, 60.0);
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
        Assert.assertEquals(50.0, tee.getMensRating(),0.01);
    }

    @Test
    public void setMensRating() {
        tee.setMensRating(83.6);
        Assert.assertEquals(83.6, tee.getMensRating(), 0.01);
    }

    @Test
    public void getWomensRating() {
        Assert.assertEquals(60.0, tee.getWomensRating(),0.01);
    }

    @Test
    public void setWomensRating() {
        tee.setWomensRating(93.6);
        Assert.assertEquals(93.6, tee.getWomensRating(), 0.01);
    }
}