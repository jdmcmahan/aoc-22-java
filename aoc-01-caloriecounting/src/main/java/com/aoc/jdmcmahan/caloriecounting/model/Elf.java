package com.aoc.jdmcmahan.caloriecounting.model;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public record Elf(int id, @Singular List<Snack> snacks) {

    public int totalCalories() {
        return snacks.stream()
                .mapToInt(Snack::calories)
                .sum();
    }
}
