package com.aoc.jdmcmahan.treetoptreehouse;

import com.aoc.jdmcmahan.treetoptreehouse.model.Forest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeVisibilityTest {

    @Test
    void testVisibility_withExampleInput() throws IOException {
        try (InputStream input = TreeVisibilityTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ForestParser parser = new ForestParser();
            Forest forest = parser.parse(input);

            assertEquals(21, forest.visible().count());
        }
    }

    @Test
    void testVisibility_withChallengeInput() throws IOException {
        try (InputStream input = TreeVisibilityTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ForestParser parser = new ForestParser();
            Forest forest = parser.parse(input);

            assertEquals(1736, forest.visible().count());
        }
    }

    @Test
    void testScenicScores_withExampleInput() throws IOException {
        try (InputStream input = TreeVisibilityTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ForestParser parser = new ForestParser();
            Forest forest = parser.parse(input);

            int bestScore = forest.trees()
                    .mapToInt(forest::scenicScore)
                    .max()
                    .orElseGet(Assertions::fail);

            assertEquals(8, bestScore);
        }
    }

    @Test
    void testScenicScores_withChallengeInput() throws IOException {
        try (InputStream input = TreeVisibilityTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ForestParser parser = new ForestParser();
            Forest forest = parser.parse(input);

            int bestScore = forest.trees()
                    .mapToInt(forest::scenicScore)
                    .max()
                    .orElseGet(Assertions::fail);

            assertEquals(268800, bestScore);
        }
    }
}
