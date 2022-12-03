package com.aoc.jdmcmahan.rockpaperscissors;

import com.aoc.jdmcmahan.rockpaperscissors.model.Outcome;
import com.aoc.jdmcmahan.rockpaperscissors.model.Round;
import com.aoc.jdmcmahan.rockpaperscissors.model.Shape;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoundListParserTest {

    @Test
    void testParseAsShapes_withSingleBlock() {
        String input = "A Y";

        RoundListParser parser = new RoundListParser();
        List<Round> parsedRounds = parser.parseAsShapes(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(1, parsedRounds.size());

        Round round1 = parsedRounds.get(0);
        assertNotNull(round1);
        assertEquals(Shape.ROCK, round1.player1Shape());
        assertEquals(Shape.PAPER, round1.player2Shape());
    }

    @Test
    void testParseAsShapes_withMultipleBlocks() {
        String input = """
                A Y
                B X
                C Z
                """;

        RoundListParser parser = new RoundListParser();
        List<Round> parsedRounds = parser.parseAsShapes(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(3, parsedRounds.size());

        Round round1 = parsedRounds.get(0);
        assertNotNull(round1);
        assertEquals(Shape.ROCK, round1.player1Shape());
        assertEquals(Shape.PAPER, round1.player2Shape());

        Round round2 = parsedRounds.get(1);
        assertNotNull(round2);
        assertEquals(Shape.PAPER, round2.player1Shape());
        assertEquals(Shape.ROCK, round2.player2Shape());

        Round round3 = parsedRounds.get(2);
        assertNotNull(round3);
        assertEquals(Shape.SCISSORS, round3.player1Shape());
        assertEquals(Shape.SCISSORS, round3.player2Shape());
    }

    @Test
    void testParseAsShapes_withEmptyInput() {
        String input = "";

        RoundListParser parser = new RoundListParser();
        Stream<Round> parsedRounds = parser.parseAsShapes(new ByteArrayInputStream(input.getBytes()));

        assertTrue(parsedRounds.findAny().isEmpty());
    }

    @Test
    void testParseAsShapesThrowsException_withMissingTokens() {
        String input = """
                A
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RoundListParser parser = new RoundListParser();

            @SuppressWarnings("unused")
            List<Round> ignored = parser.parseAsShapes(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid input on line 1: A", exception.getMessage());
    }

    @Test
    void testParseAsShapesThrowsException_withInvalidTokens() {
        String input = """
                A Q
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RoundListParser parser = new RoundListParser();

            @SuppressWarnings("unused")
            List<Round> ignored = parser.parseAsShapes(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid self shape on line 1: Q", exception.getMessage());
    }

    @Test
    void testParseAsOutcomes_withSingleBlock() {
        String input = "A Y";

        RoundListParser parser = new RoundListParser();
        List<Round> parsedRounds = parser.parseAsOutcomes(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(1, parsedRounds.size());

        Round round1 = parsedRounds.get(0);
        assertNotNull(round1);
        assertEquals(Shape.ROCK, round1.player1Shape());
        assertEquals(Outcome.DRAW, round1.player2Outcome());
    }

    @Test
    void testParseAsOutcomes_withMultipleBlocks() {
        String input = """
                A Y
                B X
                C Z
                """;

        RoundListParser parser = new RoundListParser();
        List<Round> parsedRounds = parser.parseAsOutcomes(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(3, parsedRounds.size());

        Round round1 = parsedRounds.get(0);
        assertNotNull(round1);
        assertEquals(Shape.ROCK, round1.player1Shape());
        assertEquals(Outcome.DRAW, round1.player2Outcome());

        Round round2 = parsedRounds.get(1);
        assertNotNull(round2);
        assertEquals(Shape.PAPER, round2.player1Shape());
        assertEquals(Outcome.LOSS, round2.player2Outcome());

        Round round3 = parsedRounds.get(2);
        assertNotNull(round3);
        assertEquals(Shape.SCISSORS, round3.player1Shape());
        assertEquals(Outcome.WIN, round3.player2Outcome());
    }

    @Test
    void testParseAsOutcomes_withEmptyInput() {
        String input = "";

        RoundListParser parser = new RoundListParser();
        Stream<Round> parsedRounds = parser.parseAsOutcomes(new ByteArrayInputStream(input.getBytes()));

        assertTrue(parsedRounds.findAny().isEmpty());
    }

    @Test
    void testParseAsOutcomesThrowsException_withMissingTokens() {
        String input = """
                A
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RoundListParser parser = new RoundListParser();

            @SuppressWarnings("unused")
            List<Round> ignored = parser.parseAsOutcomes(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid input on line 1: A", exception.getMessage());
    }

    @Test
    void testParseAsOutcomesThrowsException_withInvalidTokens() {
        String input = """
                A Q
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RoundListParser parser = new RoundListParser();

            @SuppressWarnings("unused")
            List<Round> ignored = parser.parseAsOutcomes(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid self outcome on line 1: Q", exception.getMessage());
    }
}
