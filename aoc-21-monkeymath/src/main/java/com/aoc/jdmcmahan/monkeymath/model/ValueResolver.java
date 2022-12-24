package com.aoc.jdmcmahan.monkeymath.model;

@FunctionalInterface
public interface ValueResolver {

    long resolve();

    default boolean hasUnisolatedBranch() {
        return this.unisolatedBranch() != null;
    }

    default ValueResolver unisolatedBranch() {
        return null;
    }

    default long isolate(long answer) {
        return this.unisolatedBranch().isolate(answer);
    }
}
