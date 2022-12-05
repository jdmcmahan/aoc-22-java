package com.aoc.jdmcmahan.rucksackreorganization.model;

import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
public final class ItemType {

    private static final Map<Character, ItemType> INTERN = new HashMap<>();

    @EqualsAndHashCode.Include
    private final char tag;

    private ItemType(char tag) {
        if (!((tag >= 'a' && tag <= 'z') || (tag >= 'A' && tag <= 'Z'))) {
            throw new IllegalArgumentException("Unrecognized tag: " + tag);
        }

        this.tag = tag;
    }

    public char tag() {
        return tag;
    }

    public int priority() {
        return priority(this.tag);
    }

    public static ItemType valueOf(char tag) {
        return INTERN.computeIfAbsent(tag, ItemType::new);
    }

    public static int priority(char tag) {
        return Character.isUpperCase(tag)
                ? tag - 'A' + 27
                : tag - 'a' + 1;
    }
}
