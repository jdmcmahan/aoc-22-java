package com.aoc.jdmcmahan.rockpaperscissors;

import com.aoc.jdmcmahan.rockpaperscissors.model.Round;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoringTest {

    @Test
    void testSelfScore_withExampleInput() throws IOException {
        try (InputStream input = ScoringTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RoundListParser parser = new RoundListParser();
            Stream<Round> rounds = parser.parseAsShapes(input);

            int score = rounds.mapToInt(Round::player2Score)
                    .sum();

            assertEquals(15, score);
        }
    }

    @Test
    void testSelfScore_withChallengeInput() throws IOException {
        try (InputStream input = ScoringTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RoundListParser parser = new RoundListParser();
            Stream<Round> rounds = parser.parseAsShapes(input);

            int score = rounds.mapToInt(Round::player2Score)
                    .sum();

            assertEquals(15691, score);
        }
    }

    @Test
    void testSelfScoreFromOutcome_withExampleInput() throws IOException {
        try (InputStream input = ScoringTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RoundListParser parser = new RoundListParser();
            Stream<Round> rounds = parser.parseAsOutcomes(input);

            int score = rounds.mapToInt(Round::player2Score)
                    .sum();

            assertEquals(12, score);
        }
    }

    @Test
    void testSelfScoreFromOutcome_withChallengeInput() throws IOException {
        try (InputStream input = ScoringTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RoundListParser parser = new RoundListParser();
            Stream<Round> rounds = parser.parseAsOutcomes(input);

            int score = rounds.mapToInt(Round::player2Score)
                    .sum();

            assertEquals(12989, score);
        }
    }
}
