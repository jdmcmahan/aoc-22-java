package com.aoc.jdmcmahan.caloriecounting;

import com.aoc.jdmcmahan.caloriecounting.model.Elf;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalorieCounterTest {

    @Test
    void testElfWithMostCalories_withExampleInput() throws IOException {
        try (InputStream input = CalorieCounterTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            CalorieListParser parser = new CalorieListParser();
            Stream<Elf> elves = parser.parse(input);

            Elf elf = ElfUtils.findElfWithMostCalories(elves);

            assertNotNull(elf);
            assertEquals(4, elf.id());
            assertEquals(24000, elf.totalCalories());
        }
    }

    @Test
    void testElfWithMostCalories_withChallengeInput() throws IOException {
        try (InputStream input = CalorieCounterTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            CalorieListParser parser = new CalorieListParser();
            Stream<Elf> elves = parser.parse(input);

            Elf elf = ElfUtils.findElfWithMostCalories(elves);

            assertNotNull(elf);
            assertEquals(69, elf.id());
            assertEquals(71023, elf.totalCalories());
        }
    }

    @Test
    void testTopThreeElves_withExampleInput() throws IOException {
        try (InputStream input = CalorieCounterTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            CalorieListParser parser = new CalorieListParser();
            Stream<Elf> elves = parser.parse(input);

            List<Elf> topElves = ElfUtils.sortElvesByCalories(elves)
                    .limit(3)
                    .toList();

            assertEquals(3, topElves.size());

            Elf elf1 = topElves.get(0);
            assertNotNull(elf1);
            assertEquals(4, elf1.id());
            assertEquals(24000, elf1.totalCalories());

            Elf elf2 = topElves.get(1);
            assertNotNull(elf2);
            assertEquals(3, elf2.id());
            assertEquals(11000, elf2.totalCalories());

            Elf elf3 = topElves.get(2);
            assertNotNull(elf3);
            assertEquals(5, elf3.id());
            assertEquals(10000, elf3.totalCalories());
        }
    }

    @Test
    void testTopThreeElves_withChallengeInput() throws IOException {
        try (InputStream input = CalorieCounterTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            CalorieListParser parser = new CalorieListParser();
            Stream<Elf> elves = parser.parse(input);

            List<Elf> topElves = ElfUtils.sortElvesByCalories(elves)
                    .limit(3)
                    .toList();

            assertEquals(3, topElves.size());

            int totalCalories = topElves.stream()
                    .mapToInt(Elf::totalCalories)
                    .sum();

            assertEquals(206289, totalCalories);

            Elf elf1 = topElves.get(0);
            assertNotNull(elf1);
            assertEquals(69, elf1.id());
            assertEquals(71023, elf1.totalCalories());

            Elf elf2 = topElves.get(1);
            assertNotNull(elf2);
            assertEquals(3, elf2.id());
            assertEquals(68034, elf2.totalCalories());

            Elf elf3 = topElves.get(2);
            assertNotNull(elf3);
            assertEquals(84, elf3.id());
            assertEquals(67232, elf3.totalCalories());
        }
    }
}
