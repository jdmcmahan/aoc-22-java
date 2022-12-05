package com.aoc.jdmcmahan.campcleanup.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SectionRangeTest {

    @Test
    void testEncloses_withNonOverlappingRanges() {
        SectionRange range1 = new SectionRange(2, 3);
        SectionRange range2 = new SectionRange(4, 5);

        assertFalse(range1.encloses(range2));
        assertFalse(range2.encloses(range1));
    }

    @Test
    void testEncloses_withPartiallyOverlappingRanges() {
        SectionRange range1 = new SectionRange(5, 7);
        SectionRange range2 = new SectionRange(7, 9);

        assertFalse(range1.encloses(range2));
        assertFalse(range2.encloses(range1));
    }

    @Test
    void testEncloses_withFullyOverlappingRanges() {
        SectionRange range1 = new SectionRange(6, 6);
        SectionRange range2 = new SectionRange(4, 6);

        assertFalse(range1.encloses(range2));
        assertTrue(range2.encloses(range1));
    }

    @Test
    void testOverlap_withNonOverlappingRanges() {
        SectionRange range1 = new SectionRange(2, 3);
        SectionRange range2 = new SectionRange(4, 5);

        assertTrue(range1.overlap(range2).isEmpty());
        assertTrue(range2.overlap(range1).isEmpty());
    }

    @Test
    void testOverlap_withPartiallyOverlappingRanges() {
        SectionRange range1 = new SectionRange(5, 7);
        SectionRange range2 = new SectionRange(7, 9);

        assertTrue(range1.overlap(range2).isPresent());
        assertTrue(range2.overlap(range1).isPresent());

        SectionRange overlap = range1.overlap(range2)
                .get();

        assertEquals(7, overlap.start());
        assertEquals(7, overlap.end());

        assertEquals(overlap, range2.overlap(range1).get());
    }

    @Test
    void testOverlap_withFullyOverlappingRanges() {
        SectionRange range1 = new SectionRange(6, 6);
        SectionRange range2 = new SectionRange(4, 6);

        assertTrue(range1.overlap(range2).isPresent());
        assertTrue(range2.overlap(range1).isPresent());

        SectionRange overlap = range1.overlap(range2)
                .get();

        assertEquals(6, overlap.start());
        assertEquals(6, overlap.end());

        assertEquals(overlap, range2.overlap(range1).get());
    }
}