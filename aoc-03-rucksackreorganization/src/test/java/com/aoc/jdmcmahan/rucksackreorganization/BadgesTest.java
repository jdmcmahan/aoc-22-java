package com.aoc.jdmcmahan.rucksackreorganization;

import com.aoc.jdmcmahan.rucksackreorganization.model.ItemType;
import com.aoc.jdmcmahan.rucksackreorganization.model.Rucksack;
import com.aoc.jdmcmahan.rucksackreorganization.model.RucksackSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgesTest {

    @Test
    void testBadges_withExampleInput() throws IOException {
        try (InputStream input = MisplacedItemsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            RucksackListParser parser = new RucksackListParser();

            List<Rucksack> rucksacks = parser.parse(input)
                    .toList();

            assertEquals(6, rucksacks.size());

            int sum = IntStream.iterate(0, i -> i < rucksacks.size(), i -> i + 3)
                    .mapToObj(i -> new RucksackSet(rucksacks.get(i), rucksacks.get(i + 1), rucksacks.get(i + 2)))
                    .map(RucksackSet::findCommonBadge)
                    .mapToInt(ItemType::priority)
                    .sum();

            assertEquals(70, sum);
        }
    }

    @Test
    void testBadges_withChallengeInput() throws IOException {
        try (InputStream input = MisplacedItemsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            RucksackListParser parser = new RucksackListParser();

            List<Rucksack> rucksacks = parser.parse(input)
                    .toList();

            int sum = IntStream.iterate(0, i -> i < rucksacks.size(), i -> i + 3)
                    .mapToObj(i -> new RucksackSet(rucksacks.get(i), rucksacks.get(i + 1), rucksacks.get(i + 2)))
                    .map(RucksackSet::findCommonBadge)
                    .mapToInt(ItemType::priority)
                    .sum();

            assertEquals(2633, sum);
        }
    }
}
