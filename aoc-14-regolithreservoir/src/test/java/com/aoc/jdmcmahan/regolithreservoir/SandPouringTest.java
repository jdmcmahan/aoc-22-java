package com.aoc.jdmcmahan.regolithreservoir;

import com.aoc.jdmcmahan.regolithreservoir.model.BottomlessCave;
import com.aoc.jdmcmahan.regolithreservoir.model.FlooredCave;
import com.aoc.jdmcmahan.regolithreservoir.model.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SandPouringTest {

    @Test
    void testSandOverflow_withExampleInput() throws IOException {
        try (InputStream input = SandPouringTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RockPathParser parser = new RockPathParser();
            List<Position> rocks = parser.parse(input);

            BottomlessCave cave = BottomlessCave.builder()
                    .rocks(rocks)
                    .build();

            Position origin = Position.valueOf(500, 0);

            while (true) {
                if (cave.pourSand(origin) == Position.VOID) {
                    break;
                }
            }

            assertEquals(24, cave.getSand().size());
        }
    }

    @Test
    void testSandOverflow_withChallengeInput() throws IOException {
        try (InputStream input = SandPouringTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RockPathParser parser = new RockPathParser();
            List<Position> rocks = parser.parse(input);

            BottomlessCave cave = BottomlessCave.builder()
                    .rocks(rocks)
                    .build();

            Position origin = Position.valueOf(500, 0);

            while (true) {
                if (cave.pourSand(origin) == Position.VOID) {
                    break;
                }
            }

            assertEquals(832, cave.getSand().size());
        }
    }

    @Test
    void testSandFloor_withExampleInput() throws IOException {
        try (InputStream input = SandPouringTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RockPathParser parser = new RockPathParser();
            List<Position> rocks = parser.parse(input);

            FlooredCave cave = FlooredCave.builder()
                    .rocks(rocks)
                    .floorOffset(2)
                    .build();

            Position origin = Position.valueOf(500, 0);

            while (true) {
                if (cave.pourSand(origin).equals(origin)) {
                    break;
                }
            }

            assertEquals(93, cave.getSand().size());
        }
    }

    @Test
    void testSandFloor_withChallengeInput() throws IOException {
        try (InputStream input = SandPouringTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RockPathParser parser = new RockPathParser();
            List<Position> rocks = parser.parse(input);

            FlooredCave cave = FlooredCave.builder()
                    .rocks(rocks)
                    .floorOffset(2)
                    .build();

            Position origin = Position.valueOf(500, 0);

            while (true) {
                if (cave.pourSand(origin).equals(origin)) {
                    break;
                }
            }

            assertEquals(27601, cave.getSand().size());
        }
    }
}
