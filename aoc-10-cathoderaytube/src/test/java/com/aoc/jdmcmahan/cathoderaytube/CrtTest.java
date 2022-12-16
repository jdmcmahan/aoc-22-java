package com.aoc.jdmcmahan.cathoderaytube;

import com.aoc.jdmcmahan.cathoderaytube.model.Cpu;
import com.aoc.jdmcmahan.cathoderaytube.model.Crt;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CrtTest {

    @Test
    void testRegisterValues_withExampleInput() throws IOException {
        try (InputStream input = CrtTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            InstructionsParser parser = new InstructionsParser();
            Cpu cpu = new Cpu();

            cpu.addListener((cycle, x, signalStrength) -> {
                switch (cycle) {
                    case 1, 2, 3 -> assertEquals(1, x);
                    case 4, 5 -> assertEquals(4, x);
                    default -> fail("Unexpected cycle: " + cycle);
                }
            });

            parser.parse(input, cpu);

            assertEquals(5, cpu.getCycle());
            assertEquals(-1, cpu.getX());
        }
    }

    @Test
    void testRegisterValues_withLargerExampleInput() throws IOException {
        try (InputStream input = CrtTest.class.getClassLoader().getResourceAsStream("example_larger.txt")) {
            InstructionsParser parser = new InstructionsParser();
            Cpu cpu = new Cpu();

            AtomicInteger signalStrengthSum = new AtomicInteger(0);
            cpu.addListener((cycle, x, signalStrength) -> {
                if (cycle == 20) {
                    assertEquals(21, x);
                    assertEquals(420, signalStrength);

                    signalStrengthSum.addAndGet(signalStrength);
                } else if (cycle == 60) {
                    assertEquals(19, x);
                    assertEquals(1140, signalStrength);

                    signalStrengthSum.addAndGet(signalStrength);
                } else if (cycle == 100) {
                    assertEquals(18, x);
                    assertEquals(1800, signalStrength);

                    signalStrengthSum.addAndGet(signalStrength);
                } else if (cycle == 140) {
                    assertEquals(21, x);
                    assertEquals(2940, signalStrength);

                    signalStrengthSum.addAndGet(signalStrength);
                } else if (cycle == 180) {
                    assertEquals(16, x);
                    assertEquals(2880, signalStrength);

                    signalStrengthSum.addAndGet(signalStrength);
                } else if (cycle == 220) {
                    assertEquals(18, x);
                    assertEquals(3960, signalStrength);

                    signalStrengthSum.addAndGet(signalStrength);
                }
            });

            parser.parse(input, cpu);

            assertEquals(240, cpu.getCycle());
            assertEquals(13140, signalStrengthSum.get());
        }
    }

    @Test
    void testRegisterValues_withChallengeInput() throws IOException {
        try (InputStream input = CrtTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            InstructionsParser parser = new InstructionsParser();
            Cpu cpu = new Cpu();

            AtomicInteger signalStrengthSum = new AtomicInteger(0);
            cpu.addListener((cycle, x, signalStrength) -> {
                if ((cycle - 20) % 40 == 0) {
                    signalStrengthSum.addAndGet(signalStrength);
                }
            });

            parser.parse(input, cpu);

            assertEquals(240, cpu.getCycle());
            assertEquals(12640, signalStrengthSum.get());
        }
    }

    @Test
    void testCrtOutput_withExampleInput() throws IOException {
        try (InputStream input = CrtTest.class.getClassLoader().getResourceAsStream("example_larger.txt")) {
            InstructionsParser parser = new InstructionsParser();
            Cpu cpu = new Cpu();
            Crt crt = new Crt(cpu, 3, 40, 6);

            parser.parse(input, cpu);

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                crt.print(out);

                String expectedOutput = """
                        ##..##..##..##..##..##..##..##..##..##..
                        ###...###...###...###...###...###...###.
                        ####....####....####....####....####....
                        #####.....#####.....#####.....#####.....
                        ######......######......######......####
                        #######.......#######.......#######.....
                        """;

                assertEquals(expectedOutput, out.toString(StandardCharsets.UTF_8));
            }
        }
    }

    @Test
    void testCrtOutput_withChallengeInput() throws IOException {
        try (InputStream input = CrtTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            InstructionsParser parser = new InstructionsParser();
            Cpu cpu = new Cpu();
            Crt crt = new Crt(cpu, 3, 40, 6);

            parser.parse(input, cpu);

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                crt.print(out);

                String expectedOutput = """
                        ####.#..#.###..####.#....###....##.###..
                        #....#..#.#..#....#.#....#..#....#.#..#.
                        ###..####.###....#..#....#..#....#.#..#.
                        #....#..#.#..#..#...#....###.....#.###..
                        #....#..#.#..#.#....#....#.#..#..#.#.#..
                        ####.#..#.###..####.####.#..#..##..#..#.
                        """;

                assertEquals(expectedOutput, out.toString(StandardCharsets.UTF_8));
            }
        }
    }
}
