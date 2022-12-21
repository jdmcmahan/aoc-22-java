package com.aoc.jdmcmahan.monkeymath.model;

public class EqualityValueResolver extends BinaryValueResolver {

    public EqualityValueResolver(ValueResolver left, ValueResolver right) {
        super(left, right);
    }

    @Override
    public long resolve() {
        ValueResolver unisolatedBranch = this.unisolatedBranch();

        if (unisolatedBranch == this.left) {
            return this.right.resolve();
        } else if (unisolatedBranch == this.right) {
            return this.left.resolve();
        } else {
            throw new IllegalStateException("No unisolated branch!");
        }
    }
}
