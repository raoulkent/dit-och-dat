package com.example.simplegolf.model;

import com.example.simplegolf.model.testcourses.TestCourses;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CourseTest {

    Course course;

    @Before
    public void setUp() {
        Course chalmersCourse = TestCourses.INSTANCE.getCourseChalmers();

        // Make a deep copy of the chalmers test course since it's a singleton.
        List<Hole> holes = new ArrayList<>();
        for (Hole h : chalmersCourse.getHoles()) {
            holes.add(new Hole(h.getHoleNumber()+1, h.getPar(), h.getHcpIndex()+1));
        }
        List<Tee> tees = new ArrayList<>();
        for (Tee t : chalmersCourse.getTees()) {
            tees.add(new Tee(t.getName(), t.getCourseRating(), t.getSlopeRating()));
        }
        course = new Course("Test course", holes, tees);
    }

    @Test
    public void testCheckUniqueHoleHcpIndicesDuplicates() {
        course.getHoles().get(0).setHcpIndex(1); // Test unique (two 1's)
        boolean result = Course.checkUniqueHoleHcpIndices(course.getHoles());
        assertFalse(result);
    }

    @Test
    public void testCheckUniqueHoleHcpIndicesRange() {
        course.getHoles().get(0).setHcpIndex(-1); // Test range
        boolean result = Course.checkUniqueHoleHcpIndices(course.getHoles());
        assertFalse(result);
    }

    @Test
    public void testCheckUniqueHoleHcpIndices() {
        boolean result = Course.checkUniqueHoleHcpIndices(course.getHoles());
        assertTrue(result);
    }

    @Test
    public void testCheckCourseSize() {
        boolean result = Course.checkCourseSize(course.getHoles());
        assertTrue(result);

        course.getHoles().add(new Hole(18, 3, 18));
        result = Course.checkCourseSize(course.getHoles());
        assertFalse(result);
    }

    @Test
    public void testSetAndGetName() {
        String expected = "Test";
        course.setName(expected);
        assertEquals(expected, course.getName());
    }

    @Test
    public void testSetAndGetTees() {
        List<Tee> tees = new ArrayList<>();
        tees.add(new Tee("TestTee", 22.0, 22.0));
        tees.add(new Tee("TestTee2", 22.0, 22.0));
        course.setTees(tees);

        assertEquals(tees.size(), course.getTees().size());
        assertEquals(tees.get(0).getName(), course.getTees().get(0).getName());
        assertEquals(tees.get(1).getName(), course.getTees().get(1).getName());
    }

    @Test
    public void testGetTotalPar() {
        assertEquals(71, course.getTotalPar());
    }

    @Test
    public void testSetAndGetHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(new Hole(1, 1, 1));
        holes.add(new Hole(2, 2, 2));
        holes.add(new Hole(3, 3, 3));
        course.setHoles(holes);

        assertEquals(3, course.getHoles().size());
        assertEquals(0, course.getHoles().get(0).getHoleNumber());
        assertEquals(1, course.getHoles().get(1).getHoleNumber());
        assertEquals(2, course.getHoles().get(2).getHoleNumber());
    }
}
