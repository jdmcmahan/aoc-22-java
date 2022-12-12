package com.aoc.jdmcmahan.ropebridge;

import com.aoc.jdmcmahan.ropebridge.model.Rope;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TailPathTest {

    @Test
    void testTailPath_withExampleInput() throws IOException {
        try (InputStream input = TailPathTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            MovementParser parser = new MovementParser();
            Rope rope = parser.parse(input, 2);

            long count = rope.getTail().getPath().stream()
                    .distinct()
                    .count();

            assertEquals(13, count);
        }
    }

    @Test
    void testTailPath_withChallengeInput() throws IOException {
        try (InputStream input = TailPathTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            MovementParser parser = new MovementParser();
            Rope rope = parser.parse(input, 2);

            long count = rope.getTail().getPath().stream()
                    .distinct()
                    .count();

            assertEquals(6081, count);
        }
    }

    @Test
    void testExtendedTailPath_withExampleInput() throws IOException {
        try (InputStream input = TailPathTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            MovementParser parser = new MovementParser();
            Rope rope = parser.parse(input, 10);

            long count = rope.getTail().getPath().stream()
                    .distinct()
                    .count();

            assertEquals(1, count);
        }
    }

    @Test
    void testExtendedTailPath_withLargerExampleInput() throws IOException {
        try (InputStream input = TailPathTest.class.getClassLoader().getResourceAsStream("example_larger.txt")) {
            MovementParser parser = new MovementParser();
            Rope rope = parser.parse(input, 10);

            long count = rope.getTail().getPath().stream()
                    .distinct()
                    .count();

            assertEquals(36, count);
        }
    }

    @Test
    void testExtendedTailPath_withChallengeInput() throws IOException {
        try (InputStream input = TailPathTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            MovementParser parser = new MovementParser();
            Rope rope = parser.parse(input, 10);

            long count = rope.getTail().getPath().stream()
                    .distinct()
                    .count();

            assertEquals(2487, count);
        }
    }
}
