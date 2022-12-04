package com.aoc.jdmcmahan.rucksackreorganization.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTypeTest {

    @Test
    void testValueOfThrowsException_whenTagIsNotAlphabetical() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> ItemType.valueOf('1'));

        assertEquals("Unrecognized tag: 1", e.getMessage());
    }

    @Test
    void testValueOf_returnsInternedItemType() {
        ItemType a1 = ItemType.valueOf('a');
        ItemType a2 = ItemType.valueOf('a');

        assertSame(a1, a2);
    }

    @Test
    void testPriority() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        for (int i = 0; i < chars.length; i++) {
            ItemType itemType = ItemType.valueOf(chars[i]);
            assertEquals(i + 1, itemType.priority());
        }
    }
}