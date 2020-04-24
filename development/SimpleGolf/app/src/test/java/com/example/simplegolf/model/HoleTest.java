package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoleTest {

    @Test
    public void testGetPar(){
        Hole hole = new Hole(3, 5);
        int expected = 5;
        int actual = hole.getPar();
        assertEquals(actual,expected);
    }

    @Test
    public void testSetPar(){
        Hole hole = new Hole(3, 5);
        int expected = 5;
        hole.setPar(expected);
        int actual = hole.getPar();
        assertEquals(actual,expected);
    }

}
