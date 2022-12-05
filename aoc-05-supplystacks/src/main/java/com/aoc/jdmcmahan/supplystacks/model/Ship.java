package com.aoc.jdmcmahan.supplystacks.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ship {

    private final Map<String, CrateStack> stacks;

    public Ship(Collection<CrateStack> stacks) {
        this.stacks = stacks.stream()
                .collect(Collectors.toMap(CrateStack::id, Function.identity()));
    }

    public Collection<CrateStack> stacks() {
        return Collections.unmodifiableCollection(this.stacks.values());
    }

    public Optional<CrateStack> stack(String id) {
        return Optional.ofNullable(stacks.get(id));
    }
}
