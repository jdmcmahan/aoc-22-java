package com.aoc.jdmcmahan.grovepositioningsystem;

import com.aoc.jdmcmahan.grovepositioningsystem.model.Coordinates;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesDecryptionTest {

    @Test
    void testCoordinatesDecryption_withExampleInput() throws IOException {
        try (InputStream input = CoordinatesDecryptionTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SequenceParser parser = new SequenceParser();
            SequenceMixer mixer = parser.parse(input, 1);

            mixer.mix();

            Coordinates coords = mixer.getCoordinates();
            assertEquals(3, coords.x() + coords.y() + coords.z());
        }
    }

    @Test
    void testCoordinatesDecryption_withChallengeInput() throws IOException {
        try (InputStream input = CoordinatesDecryptionTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SequenceParser parser = new SequenceParser();
            SequenceMixer mixer = parser.parse(input, 1);

            mixer.mix();

            Coordinates coords = mixer.getCoordinates();
            assertEquals(2215, coords.x() + coords.y() + coords.z());
        }
    }

    @Test
    void testKeyBasedCoordinatesDecryption_withExampleInput() throws IOException {
        try (InputStream input = CoordinatesDecryptionTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SequenceParser parser = new SequenceParser();
            SequenceMixer mixer = parser.parse(input, 811589153);

            for (int i = 0; i < 10; i++) {
                mixer.mix();
            }

            Coordinates coords = mixer.getCoordinates();
            assertEquals(1623178306L, coords.x() + coords.y() + coords.z());
        }
    }

    @Test
    void testKeyBasedCoordinatesDecryption_withChallengeInput() throws IOException {
        try (InputStream input = CoordinatesDecryptionTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SequenceParser parser = new SequenceParser();
            SequenceMixer mixer = parser.parse(input, 811589153);

            for (int i = 0; i < 10; i++) {
                mixer.mix();
            }

            Coordinates coords = mixer.getCoordinates();
            assertEquals(8927480683L, coords.x() + coords.y() + coords.z());
        }
    }
}
