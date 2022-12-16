package com.aoc.jdmcmahan.monkeyinthemiddle.model;

import java.util.*;

public class MonkeyRegistry {

    private final Map<String, Monkey> monkeys = new LinkedHashMap<>();

    public Collection<Monkey> getMonkeys() {
        return monkeys.values();
    }

    public Optional<Monkey> getMonkey(String id) {
        return Optional.ofNullable(monkeys.get(id));
    }

    public void registerMonkey(Monkey monkey) {
        this.monkeys.put(monkey.getId(), monkey);
    }
}
