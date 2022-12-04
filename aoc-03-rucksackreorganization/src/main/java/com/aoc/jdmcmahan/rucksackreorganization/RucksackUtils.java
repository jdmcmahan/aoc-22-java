package com.aoc.jdmcmahan.rucksackreorganization;

import com.aoc.jdmcmahan.rucksackreorganization.model.Item;
import com.aoc.jdmcmahan.rucksackreorganization.model.ItemType;
import com.aoc.jdmcmahan.rucksackreorganization.model.Rucksack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RucksackUtils {

    public static Set<ItemType> findMisplacedItemTypes(Rucksack rucksack) {
        Set<ItemType> baseTypes = extractItemTypesFromItems(rucksack.compartment1());
        Set<ItemType> otherTypes = extractItemTypesFromItems(rucksack.compartment2());

        baseTypes.retainAll(otherTypes);
        return baseTypes;
    }

    public static ItemType findCommonBadge(Rucksack rucksack1, Rucksack rucksack2, Rucksack rucksack3) {
        Set<ItemType> baseTypes = extractItemTypesFromRucksacks(rucksack1);
        Set<ItemType> otherTypes1 = extractItemTypesFromRucksacks(rucksack2);
        Set<ItemType> otherTypes2 = extractItemTypesFromRucksacks(rucksack3);

        baseTypes.retainAll(otherTypes1);
        baseTypes.retainAll(otherTypes2);

        if (baseTypes.isEmpty()) {
            throw new IllegalArgumentException("No common badge found");
        } else if (baseTypes.size() > 1) {
            throw new IllegalArgumentException("Too many common badges found: " + baseTypes);
        }

        return baseTypes.iterator().next();
    }

    private static Set<ItemType> extractItemTypesFromRucksacks(Rucksack rucksack) {
        return extractItemTypesFromItems(Stream.concat(rucksack.compartment1().stream(), rucksack.compartment2().stream()));
    }

    private static Set<ItemType> extractItemTypesFromItems(Collection<Item> items) {
        return extractItemTypesFromItems(items.stream());
    }

    private static Set<ItemType> extractItemTypesFromItems(Stream<Item> items) {
        return items.map(Item::type)
                .collect(Collectors.toSet());
    }
}
