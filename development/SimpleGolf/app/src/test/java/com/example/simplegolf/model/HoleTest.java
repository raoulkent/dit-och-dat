package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoleTest {


    @Test
    public void testGetHcp(){
       Hole hole = new Hole();
       int expected = 0;
       int actual = hole.getHcp();
       assertEquals(actual,expected);
    }

    @Test
    public void testSetHcp(){
        Hole hole = new Hole();
        int expected = 2;
        hole.setHcp(expected);
        int actual = hole.getHcp();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetPar(){
        Hole hole = new Hole();
        int expected = 0;
        int actual = hole.getPar();
        assertEquals(actual,expected);
    }

    @Test
    public void testSetPar(){
        Hole hole = new Hole();
        int expected = 5;
        hole.setPar(expected);
        int actual = hole.getPar();
        assertEquals(actual,expected);
    }

}
