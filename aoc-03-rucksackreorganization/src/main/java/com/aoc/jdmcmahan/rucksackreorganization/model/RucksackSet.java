package com.aoc.jdmcmahan.rucksackreorganization.model;

import java.util.Set;
import java.util.stream.Collectors;

public record RucksackSet(Rucksack rucksack1, Rucksack rucksack2, Rucksack rucksack3) {

    public ItemType findCommonBadge() {
        Set<ItemType> baseTypes = rucksack1.itemTypes()
                .collect(Collectors.toSet());

        Set<ItemType> otherTypes1 = rucksack2.itemTypes()
                .collect(Collectors.toSet());

        Set<ItemType> otherTypes2 = rucksack3.itemTypes()
                .collect(Collectors.toSet());

        baseTypes.retainAll(otherTypes1);
        baseTypes.retainAll(otherTypes2);

        if (baseTypes.isEmpty()) {
            throw new IllegalArgumentException("No common badge found");
        } else if (baseTypes.size() > 1) {
            throw new IllegalArgumentException("Too many common badges found: " + baseTypes);
        }

        return baseTypes.iterator().next();
    }
}
