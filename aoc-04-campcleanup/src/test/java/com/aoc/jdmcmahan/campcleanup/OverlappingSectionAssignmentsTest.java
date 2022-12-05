package com.aoc.jdmcmahan.campcleanup;

import com.aoc.jdmcmahan.campcleanup.model.SectionAssignmentPair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OverlappingSectionAssignmentsTest {

    @Test
    void testFullyOverlappingSectionAssignments_withExampleInput() throws IOException {
        try (InputStream input = OverlappingSectionAssignmentsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();

            long fullOverlapCount = parser.parse(input)
                    .filter(SectionAssignmentPair::isFullyOverlapping)
                    .count();

            assertEquals(2, fullOverlapCount);
        }
    }

    @Test
    void testFullyOverlappingSectionAssignments_withChallengeInput() throws IOException {
        try (InputStream input = OverlappingSectionAssignmentsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();

            long fullOverlapCount = parser.parse(input)
                    .filter(SectionAssignmentPair::isFullyOverlapping)
                    .count();

            assertEquals(433, fullOverlapCount);
        }
    }

    @Test
    void testOverlappingSectionAssignments_withExampleInput() throws IOException {
        try (InputStream input = OverlappingSectionAssignmentsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();

            long overlapCount = parser.parse(input)
                    .map(SectionAssignmentPair::overlap)
                    .filter(Optional::isPresent)
                    .count();

            assertEquals(4, overlapCount);
        }
    }

    @Test
    void testOverlappingSectionAssignments_withChallengeInput() throws IOException {
        try (InputStream input = OverlappingSectionAssignmentsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();

            long overlapCount = parser.parse(input)
                    .map(SectionAssignmentPair::overlap)
                    .filter(Optional::isPresent)
                    .count();

            assertEquals(852, overlapCount);
        }
    }
}
