package com.aoc.jdmcmahan.monkeymath.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnisolatedValueResolver implements ValueResolver {

    private final MonkeyRegistry registry;

    @Override
    public long resolve() {
        Monkey root = registry.getMonkey("root")
                .orElseThrow(() -> new IllegalStateException("No root monkey found"));

        ValueResolver rootResolver = root.valueResolver();

        long base = rootResolver.resolve();
        return rootResolver.isolate(base);
    }

    @Override
    public ValueResolver unisolatedBranch() {
        return this;
    }

    @Override
    public long isolate(long answer) {
        return answer;
    }
}
