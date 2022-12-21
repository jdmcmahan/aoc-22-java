package com.aoc.jdmcmahan.monkeymath.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatingValueResolver implements ValueResolver {

    private final MonkeyRegistry registry;

    private final String monkeyId;

    private Boolean hasUnisolatedBranch;

    @Override
    public long resolve() {
        return this.getResolver().resolve();
    }

    @Override
    public boolean hasUnisolatedBranch() {
        if (hasUnisolatedBranch == null) {
            hasUnisolatedBranch = this.getResolver().hasUnisolatedBranch();
        }

        return hasUnisolatedBranch;
    }

    @Override
    public ValueResolver unisolatedBranch() {
        return this.hasUnisolatedBranch()
                ? this.getResolver()
                : null;
    }

    protected ValueResolver getResolver() {
        return registry.getMonkey(monkeyId)
                .map(Monkey::valueResolver)
                .orElseThrow(() -> new IllegalArgumentException("No monkey found with id " + monkeyId));
    }
}
