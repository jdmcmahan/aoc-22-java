package com.aoc.jdmcmahan.rucksackreorganization;

import com.aoc.jdmcmahan.rucksackreorganization.model.ItemType;
import com.aoc.jdmcmahan.rucksackreorganization.model.Rucksack;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MisplacedItemsTest {

    @Test
    void testMisplacedItemTypePriority_withExampleInput() throws IOException {
        try (InputStream input = MisplacedItemsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RucksackListParser parser = new RucksackListParser();

            List<Rucksack> rucksacks = parser.parse(input)
                    .toList();

            assertEquals(6, rucksacks.size());

            assertThat(rucksacks.get(0).findMisplacedItemTypes())
                    .containsExactly(ItemType.valueOf('p'));

            assertThat(rucksacks.get(1).findMisplacedItemTypes())
                    .containsExactly(ItemType.valueOf('L'));

            assertThat(rucksacks.get(2).findMisplacedItemTypes())
                    .containsExactly(ItemType.valueOf('P'));

            assertThat(rucksacks.get(3).findMisplacedItemTypes())
                    .containsExactly(ItemType.valueOf('v'));

            assertThat(rucksacks.get(4).findMisplacedItemTypes())
                    .containsExactly(ItemType.valueOf('t'));

            assertThat(rucksacks.get(5).findMisplacedItemTypes())
                    .containsExactly(ItemType.valueOf('s'));

            int sum = rucksacks.stream()
                    .map(Rucksack::findMisplacedItemTypes)
                    .flatMap(Collection::stream)
                    .mapToInt(ItemType::priority)
                    .sum();

            assertEquals(157, sum);
        }
    }

    @Test
    void testMisplacedItemTypePriority_withChallengeInput() throws IOException {
        try (InputStream input = MisplacedItemsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RucksackListParser parser = new RucksackListParser();

            int sum = parser.parse(input)
                    .map(Rucksack::findMisplacedItemTypes)
                    .flatMap(Collection::stream)
                    .mapToInt(ItemType::priority)
                    .sum();

            assertEquals(7785, sum);
        }
    }
}
