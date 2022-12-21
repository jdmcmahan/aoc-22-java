package com.aoc.jdmcmahan.monkeymath.model;

public record Monkey(String id, ValueResolver valueResolver) {

    public long yell() {
        return valueResolver.resolve();
    }
}
