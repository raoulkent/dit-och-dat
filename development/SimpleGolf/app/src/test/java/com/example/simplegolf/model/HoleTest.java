package com.example.simplegolf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoleTest {


    @Test
    public void testGetHcp(){
       //Arrange
       Hole hole = new Hole();
       int expected = 0;

       //Act
        int actual = hole.getHcp();

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testSetHcp(){
        //Arrange
        Hole hole = new Hole();
        int expected = 2;

        //Act
        hole.setHcp(expected);
        int actual = hole.getHcp();

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testGetPar(){
        //Arrange
        Hole hole = new Hole();
        int expected = 0;

        //Act
        int actual = hole.getPar();

        //Test
        assertEquals(actual,expected);
    }

    @Test
    public void testSetPar(){
        //Arrange
        Hole hole = new Hole();
        int expected = 5;

        //Act
        hole.setPar(expected);
        int actual = hole.getPar();

        //Test
        assertEquals(actual,expected);
    }

}
