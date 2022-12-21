package com.aoc.jdmcmahan.monkeymath.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BinaryValueResolver implements ValueResolver {

    protected final ValueResolver left;

    protected final ValueResolver right;

    private Boolean hasUnisolatedBranch;

    @Override
    public boolean hasUnisolatedBranch() {
        if (this.hasUnisolatedBranch == null) {
            this.hasUnisolatedBranch = (left.hasUnisolatedBranch() || right.hasUnisolatedBranch());
        }

        return hasUnisolatedBranch;
    }

    @Override
    public ValueResolver unisolatedBranch() {
        if (this.hasUnisolatedBranch()) {
            if (left.hasUnisolatedBranch() && right.hasUnisolatedBranch()) {
                throw new IllegalStateException("Both branches reference an unisolated resolver");
            }

            if (left.hasUnisolatedBranch()) {
                return left;
            } else {
                return right;
            }
        }

        return null;
    }
}
