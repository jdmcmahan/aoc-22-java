package com.aoc.jdmcmahan.fullofhotair.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Snafu extends Number {

    public static final Snafu ZERO = new Snafu("");

    private static final char[] DIGITS = { '0', '1', '2', '=', '-' };

    private final String snafu;

    @Override
    public int intValue() {
        return (int) this.doubleValue();
    }

    @Override
    public long longValue() {
        return (long) this.doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) this.doubleValue();
    }

    @Override
    public double doubleValue() {
        double value = 0;
        for (int i = snafu.length() - 1; i >= 0; i--) {
            char current = snafu.charAt(i);
            long magnitude = (long) Math.pow(5, (snafu.length() - i - 1));

            switch (current) {
                case '-' -> value += -1 * magnitude;
                case '=' -> value += -2 * magnitude;
                default -> value += Character.getNumericValue(current) * magnitude;
            }
        }

        return value;
    }

    @Override
    public String toString() {
        return snafu;
    }

    public static Snafu valueOf(String snafu) {
        return new Snafu(snafu);
    }

    public static Snafu valueOf(long decimal) {
        if (decimal == 0) {
            return ZERO;
        }

        int remainder = (int) (decimal % 5);
        char digit = DIGITS[remainder];

        return new Snafu(valueOf((decimal + 2) / 5).snafu + digit);
    }
}
