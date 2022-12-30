package com.aoc.jdmcmahan.fullofhotair;

import com.aoc.jdmcmahan.fullofhotair.model.Snafu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BobInputTest {

    @Test
    void testConsole_withExampleInput() throws IOException {
        try (InputStream input = BobInputTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SnafuParser parser = new SnafuParser();
            Stream<Snafu> numbers = parser.parse(input);

            long sum = numbers.mapToLong(Snafu::longValue).sum();

            assertEquals(4890L, sum);
            assertEquals("2=-1=0", Snafu.valueOf(sum).toString());
        }
    }

    @Test
    void testConsole_withChallengeInput() throws IOException {
        try (InputStream input = BobInputTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SnafuParser parser = new SnafuParser();
            Stream<Snafu> numbers = parser.parse(input);

            long sum = numbers.mapToLong(Snafu::longValue).sum();

            assertEquals(34978907874317L, sum);
            assertEquals("2-1-110-=01-1-0-0==2", Snafu.valueOf(sum).toString());
        }
    }
}
