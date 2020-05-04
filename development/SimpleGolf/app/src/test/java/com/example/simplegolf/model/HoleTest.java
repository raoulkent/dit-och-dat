package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoleTest {

    @Test
    public void testGetPar() {
        Hole hole = new Hole(1, 3, 5);
        int expected = 3;
        int actual = hole.getPar();
        assertEquals(actual,expected);
    }

    @Test
    public void testSetPar() {
        Hole hole = new Hole(1, 3, 5);
        int expected = 3;
        hole.setPar(expected);
        int actual = hole.getPar();
        assertEquals(actual,expected);
    }

}
