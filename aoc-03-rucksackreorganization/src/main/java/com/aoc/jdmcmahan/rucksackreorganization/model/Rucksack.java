package com.aoc.jdmcmahan.rucksackreorganization.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Rucksack(List<Item> compartment1, List<Item> compartment2) {

    public Set<ItemType> findMisplacedItemTypes() {
        Set<ItemType> baseTypes = compartment1.stream()
                .map(Item::type)
                .collect(Collectors.toSet());

        Set<ItemType> otherTypes = compartment2.stream()
                .map(Item::type)
                .collect(Collectors.toSet());

        baseTypes.retainAll(otherTypes);
        return baseTypes;
    }

    public Stream<ItemType> itemTypes() {
        return Stream.concat(compartment1.stream(), compartment2.stream())
                .map(Item::type);
    }
}
