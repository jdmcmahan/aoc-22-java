package com.aoc.jdmcmahan.regolithreservoir.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlooredCaveTest {

    private FlooredCave cave;

    @BeforeEach
    void initializeSlice() {
        cave = FlooredCave.builder()
                .floorOffset(2)
                .rock(Position.valueOf(498, 4))
                .rock(Position.valueOf(498, 5))
                .rock(Position.valueOf(498, 6))
                .rock(Position.valueOf(497, 6))
                .rock(Position.valueOf(496, 6))
                .rock(Position.valueOf(503, 4))
                .rock(Position.valueOf(502, 4))
                .rock(Position.valueOf(502, 5))
                .rock(Position.valueOf(502, 6))
                .rock(Position.valueOf(502, 7))
                .rock(Position.valueOf(502, 8))
                .rock(Position.valueOf(502, 9))
                .rock(Position.valueOf(501, 9))
                .rock(Position.valueOf(500, 9))
                .rock(Position.valueOf(499, 9))
                .rock(Position.valueOf(498, 9))
                .rock(Position.valueOf(497, 9))
                .rock(Position.valueOf(496, 9))
                .rock(Position.valueOf(495, 9))
                .rock(Position.valueOf(494, 9))
                .build();
    }

    @Test
    void testPourSand_withSmallPileOfSand() {
        Position origin = Position.valueOf(500, 0);

        assertEquals(Position.valueOf(500, 8), cave.pourSand(origin));
        assertEquals(Position.valueOf(499, 8), cave.pourSand(origin));
        assertEquals(Position.valueOf(501, 8), cave.pourSand(origin));
        assertEquals(Position.valueOf(500, 7), cave.pourSand(origin));
        assertEquals(Position.valueOf(498, 8), cave.pourSand(origin));
    }

    @Test
    void testPourSand_withBottomedOutSand() {
        Position origin = Position.valueOf(500, 0);

        for (int i = 0; i < 93; i++) {
            cave.pourSand(origin);
        }

        Position[] expected = {
                Position.valueOf(490, 10),
                Position.valueOf(491, 9),
                Position.valueOf(491, 10),
                Position.valueOf(492, 8),
                Position.valueOf(492, 9),
                Position.valueOf(492, 10),
                Position.valueOf(493, 7),
                Position.valueOf(493, 8),
                Position.valueOf(493, 9),
                Position.valueOf(493, 10),
                Position.valueOf(494, 6),
                Position.valueOf(494, 7),
                Position.valueOf(494, 8),
                Position.valueOf(494, 10),
                Position.valueOf(495, 5),
                Position.valueOf(495, 6),
                Position.valueOf(495, 7),
                Position.valueOf(495, 8),
                Position.valueOf(496, 4),
                Position.valueOf(496, 5),
                Position.valueOf(496, 7),
                Position.valueOf(496, 8),
                Position.valueOf(497, 3),
                Position.valueOf(497, 4),
                Position.valueOf(497, 5),
                Position.valueOf(497, 8),
                Position.valueOf(498, 2),
                Position.valueOf(498, 3),
                Position.valueOf(498, 7),
                Position.valueOf(498, 8),
                Position.valueOf(499, 1),
                Position.valueOf(499, 2),
                Position.valueOf(499, 3),
                Position.valueOf(499, 4),
                Position.valueOf(499, 5),
                Position.valueOf(499, 6),
                Position.valueOf(499, 7),
                Position.valueOf(499, 8),
                Position.valueOf(500, 0),
                Position.valueOf(500, 1),
                Position.valueOf(500, 2),
                Position.valueOf(500, 3),
                Position.valueOf(500, 4),
                Position.valueOf(500, 5),
                Position.valueOf(500, 6),
                Position.valueOf(500, 7),
                Position.valueOf(500, 8),
                Position.valueOf(501, 1),
                Position.valueOf(501, 2),
                Position.valueOf(501, 3),
                Position.valueOf(501, 4),
                Position.valueOf(501, 5),
                Position.valueOf(501, 6),
                Position.valueOf(501, 7),
                Position.valueOf(501, 8),
                Position.valueOf(502, 2),
                Position.valueOf(502, 3),
                Position.valueOf(502, 10),
                Position.valueOf(503, 3),
                Position.valueOf(503, 5),
                Position.valueOf(503, 6),
                Position.valueOf(503, 7),
                Position.valueOf(503, 8),
                Position.valueOf(503, 9),
                Position.valueOf(503, 10),
                Position.valueOf(504, 4),
                Position.valueOf(504, 5),
                Position.valueOf(504, 6),
                Position.valueOf(504, 7),
                Position.valueOf(504, 8),
                Position.valueOf(504, 9),
                Position.valueOf(504, 10),
                Position.valueOf(505, 5),
                Position.valueOf(505, 6),
                Position.valueOf(505, 7),
                Position.valueOf(505, 8),
                Position.valueOf(505, 9),
                Position.valueOf(505, 10),
                Position.valueOf(506, 6),
                Position.valueOf(506, 7),
                Position.valueOf(506, 8),
                Position.valueOf(506, 9),
                Position.valueOf(506, 10),
                Position.valueOf(507, 7),
                Position.valueOf(507, 8),
                Position.valueOf(507, 9),
                Position.valueOf(507, 10),
                Position.valueOf(508, 8),
                Position.valueOf(508, 9),
                Position.valueOf(508, 10),
                Position.valueOf(509, 9),
                Position.valueOf(509, 10),
                Position.valueOf(510, 10)
        };

        assertThat(cave.getSand())
                .containsExactlyInAnyOrder(expected);

        Position next = cave.pourSand(origin);
        assertEquals(origin, next);
    }
}