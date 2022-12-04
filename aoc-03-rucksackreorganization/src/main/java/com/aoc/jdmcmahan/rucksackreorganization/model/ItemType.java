package com.aoc.jdmcmahan.rucksackreorganization.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ItemType {

    private static final Map<Character, ItemType> INTERN = new HashMap<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemType itemType = (ItemType) o;
        return tag == itemType.tag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
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
