package com.aoc.jdmcmahan.campcleanup.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SectionAssignmentPairTest {

    @Test
    void testIsFullyOverlapping_withNonOverlappingRanges() {
        SectionAssignment section1 = new SectionAssignment(2, 3);
        SectionAssignment section2 = new SectionAssignment(4, 5);

        SectionAssignmentPair pair = new SectionAssignmentPair(section1, section2);
        assertFalse(pair.isFullyOverlapping());

        pair = new SectionAssignmentPair(section2, section1);
        assertFalse(pair.isFullyOverlapping());
    }

    @Test
    void testIsFullyOverlapping_withPartiallyOverlappingRanges() {
        SectionAssignment section1 = new SectionAssignment(5, 7);
        SectionAssignment section2 = new SectionAssignment(7, 9);

        SectionAssignmentPair pair = new SectionAssignmentPair(section1, section2);
        assertFalse(pair.isFullyOverlapping());

        pair = new SectionAssignmentPair(section2, section1);
        assertFalse(pair.isFullyOverlapping());
    }

    @Test
    void testIsFullyOverlapping_withFullyOverlappingRanges() {
        SectionAssignment section1 = new SectionAssignment(6, 6);
        SectionAssignment section2 = new SectionAssignment(4, 6);

        SectionAssignmentPair pair = new SectionAssignmentPair(section1, section2);
        assertTrue(pair.isFullyOverlapping());

        pair = new SectionAssignmentPair(section2, section1);
        assertTrue(pair.isFullyOverlapping());
    }

    @Test
    void testOverlap_withNonOverlappingRanges() {
        SectionAssignment section1 = new SectionAssignment(2, 3);
        SectionAssignment section2 = new SectionAssignment(4, 5);

        SectionAssignmentPair pair = new SectionAssignmentPair(section1, section2);
        assertTrue(pair.overlap().isEmpty());

        pair = new SectionAssignmentPair(section2, section1);
        assertTrue(pair.overlap().isEmpty());
    }

    @Test
    void testOverlap_withPartiallyOverlappingRanges() {
        SectionAssignment section1 = new SectionAssignment(5, 7);
        SectionAssignment section2 = new SectionAssignment(7, 9);

        SectionAssignmentPair pair = new SectionAssignmentPair(section1, section2);
        Optional<SectionRange> overlap = pair.overlap();

        assertFalse(overlap.isEmpty());
        assertEquals(7, overlap.get().start());
        assertEquals(7, overlap.get().end());

        pair = new SectionAssignmentPair(section2, section1);
        overlap = pair.overlap();

        assertFalse(overlap.isEmpty());
        assertEquals(7, overlap.get().start());
        assertEquals(7, overlap.get().end());
    }

    @Test
    void testOverlap_withFullyOverlappingRanges() {
        SectionAssignment section1 = new SectionAssignment(6, 6);
        SectionAssignment section2 = new SectionAssignment(4, 6);

        SectionAssignmentPair pair = new SectionAssignmentPair(section1, section2);
        Optional<SectionRange> overlap = pair.overlap();

        assertFalse(overlap.isEmpty());
        assertEquals(6, overlap.get().start());
        assertEquals(6, overlap.get().end());

        pair = new SectionAssignmentPair(section2, section1);
        overlap = pair.overlap();

        assertFalse(overlap.isEmpty());
        assertEquals(6, overlap.get().start());
        assertEquals(6, overlap.get().end());
    }
}