package com.aoc.jdmcmahan.monkeymath.model;

import java.util.function.BiFunction;

public abstract class ArithmeticValueResolver extends BinaryValueResolver {

    private final BiFunction<Long, Long, Long> operation;

    public ArithmeticValueResolver(ValueResolver left, ValueResolver right, BiFunction<Long, Long, Long> operation) {
        super(left, right);

        this.operation = operation;
    }

    @Override
    public long resolve() {
        long leftValue = left.resolve();
        long rightValue = right.resolve();

        return operation.apply(leftValue, rightValue);
    }

    public static ArithmeticValueResolver adding(ValueResolver left, ValueResolver right) {
        return new AddingValueResolver(left, right);
    }

    public static ArithmeticValueResolver subtracting(ValueResolver left, ValueResolver right) {
        return new SubtractingValueResolver(left, right);
    }

    public static ArithmeticValueResolver multiplying(ValueResolver left, ValueResolver right) {
        return new MultiplyingValueResolver(left, right);
    }

    public static ArithmeticValueResolver dividing(ValueResolver left, ValueResolver right) {
        return new DividingValueResolver(left, right);
    }

    public static class AddingValueResolver extends ArithmeticValueResolver {

        public AddingValueResolver(ValueResolver left, ValueResolver right) {
            super(left, right, Long::sum);
        }

        @Override
        public long isolate(long answer) {
            ValueResolver unisolatedBranch = this.unisolatedBranch();

            if (unisolatedBranch == this.left) {
                return unisolatedBranch.isolate(answer - this.right.resolve());
            } else if (unisolatedBranch == this.right) {
                return unisolatedBranch.isolate(answer - this.left.resolve());
            }

            throw new IllegalStateException("No unisolated branch!");
        }
    }

    public static class SubtractingValueResolver extends ArithmeticValueResolver {

        public SubtractingValueResolver(ValueResolver left, ValueResolver right) {
            super(left, right, (l, r) -> l - r);
        }

        @Override
        public long isolate(long answer) {
            ValueResolver unisolatedBranch = this.unisolatedBranch();

            if (unisolatedBranch == this.left) {
                return unisolatedBranch.isolate(answer + this.right.resolve());
            } else if (unisolatedBranch == this.right) {
                return unisolatedBranch.isolate(this.left.resolve() - answer);
            }

            throw new IllegalStateException("No unisolated branch!");
        }
    }

    public static class MultiplyingValueResolver extends ArithmeticValueResolver {

        public MultiplyingValueResolver(ValueResolver left, ValueResolver right) {
            super(left, right, (l, r) -> l * r);
        }

        @Override
        public long isolate(long answer) {
            ValueResolver unisolatedBranch = this.unisolatedBranch();

            if (unisolatedBranch == this.left) {
                return unisolatedBranch.isolate(answer / this.right.resolve());
            } else if (unisolatedBranch == this.right) {
                return unisolatedBranch.isolate(answer / this.left.resolve());
            }

            throw new IllegalStateException("No unisolated branch!");
        }
    }

    public static class DividingValueResolver extends ArithmeticValueResolver {

        public DividingValueResolver(ValueResolver left, ValueResolver right) {
            super(left, right, (l, r) -> l / r);
        }

        @Override
        public long isolate(long answer) {
            ValueResolver unisolatedBranch = this.unisolatedBranch();

            if (unisolatedBranch == this.left) {
                return unisolatedBranch.isolate(answer * this.right.resolve());
            } else if (unisolatedBranch == this.right) {
                return unisolatedBranch.isolate(this.left.resolve() / answer);
            }

            throw new IllegalStateException("No unisolated branch!");
        }
    }
}
