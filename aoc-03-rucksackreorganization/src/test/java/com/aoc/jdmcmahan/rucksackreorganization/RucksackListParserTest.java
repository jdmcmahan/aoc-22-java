package com.aoc.jdmcmahan.rucksackreorganization;

import com.aoc.jdmcmahan.rucksackreorganization.model.Item;
import com.aoc.jdmcmahan.rucksackreorganization.model.Rucksack;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RucksackListParserTest {

    @Test
    void testParse_withSingleBlock() {
        String input = "vJrwpWtwJgWrhcsFMMfFFhFp";

        RucksackListParser parser = new RucksackListParser();
        List<Rucksack> rucksacks = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(1, rucksacks.size());

        Rucksack rucksack1 = rucksacks.get(0);
        assertNotNull(rucksack1);

        assertThat(rucksack1.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("vJrwpWtwJgWr"));

        assertThat(rucksack1.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("hcsFMMfFFhFp"));
    }

    @Test
    void testParse_withMultipleBlocks() {
        String input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;

        RucksackListParser parser = new RucksackListParser();
        List<Rucksack> rucksacks = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(6, rucksacks.size());

        Rucksack rucksack1 = rucksacks.get(0);
        assertNotNull(rucksack1);

        assertThat(rucksack1.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("vJrwpWtwJgWr"));

        assertThat(rucksack1.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("hcsFMMfFFhFp"));

        Rucksack rucksack2 = rucksacks.get(1);
        assertNotNull(rucksack2);

        assertThat(rucksack2.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("jqHRNqRjqzjGDLGL"));

        assertThat(rucksack2.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("rsFMfFZSrLrFZsSL"));

        Rucksack rucksack3 = rucksacks.get(2);
        assertNotNull(rucksack3);

        assertThat(rucksack3.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("PmmdzqPrV"));

        assertThat(rucksack3.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("vPwwTWBwg"));

        Rucksack rucksack4 = rucksacks.get(3);
        assertNotNull(rucksack4);

        assertThat(rucksack4.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("wMqvLMZHhHMvwLH"));

        assertThat(rucksack4.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("jbvcjnnSBnvTQFn"));

        Rucksack rucksack5 = rucksacks.get(4);
        assertNotNull(rucksack5);

        assertThat(rucksack5.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("ttgJtRGJ"));

        assertThat(rucksack5.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("QctTZtZT"));

        Rucksack rucksack6 = rucksacks.get(5);
        assertNotNull(rucksack6);

        assertThat(rucksack6.compartment1())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("CrZsJsPPZsGz"));

        assertThat(rucksack6.compartment2())
                .extracting(item -> item.type().tag())
                .containsExactlyElementsOf(stringToBoxedCharacters("wwsLwLmpwMDw"));
    }

    @Test
    void testParse_withEmptyInput() {
        String input = "";

        RucksackListParser parser = new RucksackListParser();
        Stream<Rucksack> rucksacks = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertTrue(rucksacks.findAny().isEmpty());
    }

    @Test
    void testParseThrowsException_withInvalidItems() {
        String input = """
                ABC123
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RucksackListParser parser = new RucksackListParser();

            @SuppressWarnings("unused")
            List<Rucksack> ignored = parser.parse(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid data on line 1: ABC123", exception.getMessage());
    }

    @Test
    void testStringToItems_withSimpleInput() {
        String s = "vJrwpWtwJgWr";
        List<Item> items = RucksackListParser.stringToItems(s);

        assertEquals(s.length(), items.size());

        IntStream.range(0, s.length())
                .forEach(i -> assertEquals(s.charAt(i), items.get(i).type().tag()));
    }

    @Test
    void testStringToItems_withEmptyInput() {
        String s = "";
        List<Item> items = RucksackListParser.stringToItems(s);

        assertEquals(0, items.size());
    }

    @Test
    void testStringToItemsThrowsException_withInvalidTypes() {
        String s = "1234";

        assertThrows(IllegalArgumentException.class, () -> RucksackListParser.stringToItems(s));
    }

    private static List<Character> stringToBoxedCharacters(String s) {
        return s.chars()
                .mapToObj(i -> (char) i)
                .toList();
    }
}