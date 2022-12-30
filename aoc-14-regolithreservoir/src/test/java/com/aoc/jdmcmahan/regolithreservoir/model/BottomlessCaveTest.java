package com.aoc.jdmcmahan.regolithreservoir.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BottomlessCaveTest {

    private BottomlessCave cave;

    @BeforeEach
    void initializeSlice() {
        cave = BottomlessCave.builder()
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
    void testPourSand_withOverflowingSand() {
        Position origin = Position.valueOf(500, 0);

        for (int i = 0; i < 24; i++) {
            cave.pourSand(origin);
        }

        Position[] expected = {
                Position.valueOf(495, 8),
                Position.valueOf(497, 5),
                Position.valueOf(497, 8),
                Position.valueOf(498, 7),
                Position.valueOf(498, 8),
                Position.valueOf(499, 3),
                Position.valueOf(499, 4),
                Position.valueOf(499, 5),
                Position.valueOf(499, 6),
                Position.valueOf(499, 7),
                Position.valueOf(499, 8),
                Position.valueOf(500, 2),
                Position.valueOf(500, 3),
                Position.valueOf(500, 4),
                Position.valueOf(500, 5),
                Position.valueOf(500, 6),
                Position.valueOf(500, 7),
                Position.valueOf(500, 8),
                Position.valueOf(501, 3),
                Position.valueOf(501, 4),
                Position.valueOf(501, 5),
                Position.valueOf(501, 6),
                Position.valueOf(501, 7),
                Position.valueOf(501, 8)
        };

        assertThat(cave.getSand())
                .containsExactlyInAnyOrder(expected);

        Position next = cave.pourSand(origin);
        assertEquals(Position.VOID, next);
    }
}