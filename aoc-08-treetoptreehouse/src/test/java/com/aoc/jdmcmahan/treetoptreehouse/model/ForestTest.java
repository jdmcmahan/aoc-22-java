package com.aoc.jdmcmahan.treetoptreehouse.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForestTest {

    private Forest forest;

    @BeforeEach
    void initializeForest() {
        forest = Forest.builder()
                .row(3, 0, 3, 7, 3)
                .row(2, 5, 5, 1, 2)
                .row(6, 5, 3, 3, 2)
                .row(3, 3, 5, 4, 9)
                .row(3, 5, 3, 9, 0)
                .build();
    }

    @Test
    void testVisible() {
        assertEquals(21, forest.visible().count());
    }
}