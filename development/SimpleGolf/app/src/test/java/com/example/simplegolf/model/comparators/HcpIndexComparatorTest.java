package com.example.simplegolf.model.comparators;

import com.example.simplegolf.model.Hole;

import org.junit.Test;

import java.util.Comparator;

import static junit.framework.TestCase.assertEquals;

public class HcpIndexComparatorTest {

    @Test
    public void testCompare() {
        Hole a = new Hole(1, 99, 1);
        Hole b = new Hole(2, 99, 2);
        Hole c = new Hole(3, 99, 2);
        Comparator<Hole> comparator = new HcpIndexComparator();

        int result = comparator.compare(a, b);
        assertEquals(-1, result);

        result = comparator.compare(b, c);
        assertEquals(0, result);

        result = comparator.compare(b, a);
        assertEquals(1, result);
    }
}
