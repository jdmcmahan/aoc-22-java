package com.aoc.jdmcmahan.caloriecounting;

import com.aoc.jdmcmahan.caloriecounting.model.Elf;

import java.util.Comparator;
import java.util.stream.Stream;

public final class ElfUtils {

    public static Elf findElfWithMostCalories(Stream<Elf> elves) {
        return elves.max(Comparator.comparing(Elf::totalCalories))
                .orElse(null);
    }

    public static Stream<Elf> sortElvesByCalories(Stream<Elf> elves) {
        return elves.sorted(Comparator.comparing(Elf::totalCalories).reversed());
    }

    private ElfUtils() {}
}
