package com.aoc.jdmcmahan.campcleanup;

import com.aoc.jdmcmahan.campcleanup.model.SectionAssignmentPair;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SectionAssignmentPairListParserTest {

    @Test
    void testParse_withSingleBlock() {
        String input = "2-4,6-8";

        SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();
        List<SectionAssignmentPair> rucksacks = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(1, rucksacks.size());

        SectionAssignmentPair pair1 = rucksacks.get(0);
        assertNotNull(pair1);

        assertEquals(2, pair1.assignment1().range().start());
        assertEquals(4, pair1.assignment1().range().end());

        assertEquals(6, pair1.assignment2().range().start());
        assertEquals(8, pair1.assignment2().range().end());
    }

    @Test
    void testParse_withMultipleBlocks() {
        String input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;

        SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();
        List<SectionAssignmentPair> rucksacks = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(6, rucksacks.size());

        SectionAssignmentPair pair1 = rucksacks.get(0);
        assertNotNull(pair1);

        assertEquals(2, pair1.assignment1().range().start());
        assertEquals(4, pair1.assignment1().range().end());

        assertEquals(6, pair1.assignment2().range().start());
        assertEquals(8, pair1.assignment2().range().end());

        SectionAssignmentPair pair2 = rucksacks.get(1);
        assertNotNull(pair2);

        assertEquals(2, pair2.assignment1().range().start());
        assertEquals(3, pair2.assignment1().range().end());

        assertEquals(4, pair2.assignment2().range().start());
        assertEquals(5, pair2.assignment2().range().end());

        SectionAssignmentPair pair3 = rucksacks.get(2);
        assertNotNull(pair3);

        assertEquals(5, pair3.assignment1().range().start());
        assertEquals(7, pair3.assignment1().range().end());

        assertEquals(7, pair3.assignment2().range().start());
        assertEquals(9, pair3.assignment2().range().end());

        SectionAssignmentPair pair4 = rucksacks.get(3);
        assertNotNull(pair4);

        assertEquals(2, pair4.assignment1().range().start());
        assertEquals(8, pair4.assignment1().range().end());

        assertEquals(3, pair4.assignment2().range().start());
        assertEquals(7, pair4.assignment2().range().end());

        SectionAssignmentPair pair5 = rucksacks.get(4);
        assertNotNull(pair5);

        assertEquals(6, pair5.assignment1().range().start());
        assertEquals(6, pair5.assignment1().range().end());

        assertEquals(4, pair5.assignment2().range().start());
        assertEquals(6, pair5.assignment2().range().end());

        SectionAssignmentPair pair6 = rucksacks.get(5);
        assertNotNull(pair6);

        assertEquals(2, pair6.assignment1().range().start());
        assertEquals(6, pair6.assignment1().range().end());

        assertEquals(4, pair6.assignment2().range().start());
        assertEquals(8, pair6.assignment2().range().end());
    }

    @Test
    void testParse_withEmptyInput() {
        String input = "";

        SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();
        Stream<SectionAssignmentPair> pairs = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertTrue(pairs.findAny().isEmpty());
    }

    @Test
    void testParseThrowsException_withInvalidPairs() {
        String input = """
                2-4,a-b
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            SectionAssignmentPairListParser parser = new SectionAssignmentPairListParser();

            @SuppressWarnings("unused")
            List<SectionAssignmentPair> ignored = parser.parse(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid input at line 1: 2-4,a-b", exception.getMessage());
    }
}