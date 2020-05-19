package com.example.simplegolf.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoleTest {

    private Hole hole;

    @Before
    public void setUp() {
        hole = new Hole(1, 3, 5);
    }

    @Test
    public void testGetPar() {
        assertEquals(3, hole.getPar());
    }

    @Test
    public void testSetPar() {
        int expected = 3;
        hole.setPar(expected);
        assertEquals(expected, hole.getPar());
    }

    @Test
    public void testGetHcpIndex() {
        assertEquals(4, hole.getHcpIndex());
    }

    @Test
    public void testSetHcpIndex() {
        hole.setHcpIndex(6);
        assertEquals(5, hole.getHcpIndex());
    }

    @Test
    public void testGetHoleNumber() {
        assertEquals(0, hole.getHoleNumber());
    }

    @Test
    public void testSetHoleNumber() {
        hole.setHoleNumber(2);
        assertEquals(1, hole.getHoleNumber());
    }
}
