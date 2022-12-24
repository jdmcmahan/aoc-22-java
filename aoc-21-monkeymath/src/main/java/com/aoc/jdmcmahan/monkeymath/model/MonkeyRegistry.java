package com.aoc.jdmcmahan.monkeymath.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class MonkeyRegistry {

    private final Map<String, Monkey> monkeys = new LinkedHashMap<>();

    public Collection<Monkey> getMonkeys() {
        return monkeys.values();
    }

    public Optional<Monkey> getMonkey(String id) {
        return Optional.ofNullable(monkeys.get(id));
    }

    public void registerMonkey(Monkey monkey) {
        this.monkeys.put(monkey.id(), monkey);
    }
}
