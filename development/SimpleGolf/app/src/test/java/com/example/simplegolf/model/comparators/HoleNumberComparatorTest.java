package com.example.simplegolf.model.comparators;

import com.example.simplegolf.model.Hole;

import org.junit.Test;

import java.util.Comparator;

import static junit.framework.TestCase.assertEquals;

public class HoleNumberComparatorTest {

    @Test
    public void testCompare() {
        Hole a = new Hole(1, 99, 99);
        Hole b = new Hole(2, 99, 99);
        Hole c = new Hole(2, 99, 99);
        Comparator<Hole> comparator = new HoleNumberComparator();

        int result = comparator.compare(a, b);
        assertEquals(-1, result);

        result = comparator.compare(b, c);
        assertEquals(0, result);

        result = comparator.compare(b, a);
        assertEquals(1, result);
    }
}
